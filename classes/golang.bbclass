inherit golang-osarchmap ptest

GO_PARALLEL_BUILD ?= "${@d.getVar('PARALLEL_MAKE', True).replace('-j', '-p')}"
BB_HASHBASE_WHITELIST_append = " GO_PARALLEL_BUILD GO_TMPDIR"
BB_HASHCONFIG_WHITELIST_append = " GO_PARALLEL_BUILD GO_TMPDIR"

DEPENDS_GOLANG_class-target = "virtual/${TARGET_PREFIX}golang virtual/${TARGET_PREFIX}golang-runtime"
DEPENDS_GOLANG_class-nativesdk = "virtual/${TARGET_PREFIX}golang-crosssdk virtual/${TARGET_PREFIX}golang-runtime"
DEPENDS_GOLANG_class-native = "golang-native"

DEPENDS_append = " ${DEPENDS_GOLANG}"

export GO = "${HOST_PREFIX}go"
export GOPATH = "${B}"
GO_LINKSHARED ?= "${@['', '-linkshared'][d.getVar('GO_SHLIBS_SUPPORTED', True) == '1']}"
GO_BUILD_SHLIBS ?= "0"
GO_EXTLDFLAGS ?= '-ldflags="-extldflags '${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} ${LDFLAGS}'"'
export GOBUILDFLAGS ?= "-x -v ${GO_EXTLDFLAGS}"
export GOPTESTBUILDFLAGS ?= "${GOBUILDFLAGS} -c"
export GOPTESTFLAGS ?= "-test.v"
GOBUILDFLAGS_prepend_task-compile = "${GO_PARALLEL_BUILD} "

GO_BUILDBIN = "${B}/${GO_BUILD_BINDIR}"
GO_BUILDBIN_class-native = "${B}/bin"

S = "${WORKDIR}/go/src"
B = "${WORKDIR}/build"
GO_TMPDIR = "${WORKDIR}/go-tmp"

GO_INSTALL ?= "${GO_SRCROOT}/..."
GO_INSTALL_FILTEROUT ?= "/vendor/"

GOROOT = "${STAGING_LIBDIR}/go"
GOROOT_class-native = "${STAGING_LIBDIR_NATIVE}/go"
GOROOT_class-nativesdk = "${STAGING_DIR_TARGET}${libdir}/go"
export GOROOT
GOTOOLDIR = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/go/pkg/tool/${BUILD_GOTUPLE}"
GOTOOLDIR_class-native = "${STAGING_LIBDIR_NATIVE}/go/pkg/tool/${BUILD_GOTUPLE}"
export GOTOOLDIR
export GO_SHLIBDIR ?= "${libdir}/go/pkg/${TARGET_GOTUPLE}_dynlink"

export CGO_ENABLED ?= "${@['0', '1'][d.getVar('GO_SHLIBS_SUPPORTED', True) == '1']}"
export CGO_LDFLAGS ?= "${LDFLAGS}"

do_unpack[dirs] = "${S}"
python golang_do_unpack() {
    src_uri = (d.getVar('SRC_URI', True) or "").split()
    if len(src_uri) == 0:
        return

    # Ensure that we cleanup ${S}/patches
    # TODO: Investigate if we can remove
    # the entire ${S} in this case.
    s_dir = d.getVar('S', True)
    p_dir = os.path.join(s_dir, 'patches')
    bb.utils.remove(p_dir, True)

    srcroot = d.getVar('GO_SRCROOT', True)
    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        for url in fetcher.urls:
            if fetcher.ud[url].type == 'git':
                if fetcher.ud[url].parm.get('destsuffix') is None:
                    fetcher.ud[url].parm['destsuffix'] = srcroot + '/'
        fetcher.unpack(s_dir)
    except bb.fetch2.BBFetchException as e:
        raise bb.build.FuncFailed(e)
}

golang_list_packages() {
    ${GO} list -f '{{.ImportPath}}' ${GOBUILDFLAGS} ${GO_INSTALL} | egrep -v '${GO_INSTALL_FILTEROUT}'
}

do_configure[dirs] = "${B}"
do_configure[cleandirs] = "${B}"
golang_do_configure() {
    ln -snf ${S} ${B}/
    rm -f ${B}/.go_compile_ptest.list
    ${GO} list -f '{{.ImportPath}} {{.TestGoFiles}}' ${GOBUILDFLAGS} ${GO_INSTALL} | grep -v '\[\]$' | egrep -v '${GO_INSTALL_FILTEROUT}' | awk '{print $1}' >${B}/.go_compile_ptest.list
    ${GO} list -f '{{.ImportPath}} {{.Incomplete}}' ${GOBUILDFLAGS} `golang_list_packages` | while read pkg inc; do
        if $inc; then
            bberror "${PN}: package $pkg is missing dependencies"
        fi
    done
}

do_compile[dirs] =+ "${B}/bin ${B}/pkg ${GO_TMPDIR}"
do_compile[cleandirs] = "${B}/bin ${B}/pkg ${GO_TMPDIR}"

golang_do_compile() {
    export TMPDIR="${GO_TMPDIR}"
    if [ "${GO_BUILD_SHLIBS}" = "1" ]; then
        rm -f ${B}/build-shared.list
        ${GO} install -n -buildmode=shared ${GO_LINKSHARED} ${GOBUILDFLAGS} `golang_list_packages` 2>&1 | grep '^# ' | sed -e's,^# ,,' > ${B}/build-shared.list
        while read pkg; do
            ${GO} install -buildmode=shared ${GO_LINKSHARED} ${GOBUILDFLAGS} $pkg
        done < ${B}/build-shared.list
        ${GO} install -buildmode=exe ${GO_LINKSHARED} ${GOBUILDFLAGS} `golang_list_packages`
    else
        ${GO} install ${GO_LINKSHARED} ${GOBUILDFLAGS} `golang_list_packages`
    fi
}

golang_do_install() {
    if [ "${GO_BUILD_SHLIBS}" = "1" ]; then
        for f in ${B}/pkg/${TARGET_GOTUPLE}_dynlink/*${SOLIBSDEV}; do
            if [ -e "$f" ]; then
                chrpath -r "${GO_SHLIBDIR}" $f
            fi
        done
    fi
    didbindir=""
    install -d ${D}${libdir}/go/src/${GO_SRCROOT}
    tar -C ${S}/${GO_SRCROOT} -c -f- --exclude-vcs --exclude "*.test" . | tar -C ${D}${libdir}/go/src/${GO_SRCROOT} --no-same-owner -x -f-
    for tgtfile in ${GO_BUILDBIN}/*; do
        [ -e $tgtfile ] || continue
        if [ "${GO_SHLIBS_SUPPORTED}" = "1" ]; then
            chrpath -r "${GO_SHLIBDIR}" $tgtfile
        fi
        if [ -z "$didbindir" ]; then                
            install -d ${D}${bindir}
            didbindir="yes"
        fi
        install -m 0755 $tgtfile ${D}${bindir}
    done
    install -d ${D}${libdir}/go
    cp --preserve=mode,timestamps -R ${B}/pkg ${D}${libdir}/go/
}

do_compile_ptest() {
    curwpd=$PWD
    rm -f ${B}/.go_compiled_tests.list
    while read pkg; do
        cd ${S}/$pkg
        ${GO} test ${GOPTESTBUILDFLAGS} $pkg
        find . -mindepth 1 -maxdepth 1 -type f -name '*.test' -exec echo $pkg/{} \; | sed -e's,/\./,/,' >>${B}/.go_compiled_tests.list
    done < ${B}/.go_compile_ptest.list
    cd $curpwd
}

do_install_ptest_base() {
    test -f "${B}/.go_compiled_tests.list" || exit 0
    tests=""
    while read test; do
        tests="$tests${tests:+ }${test%.test}"
        testdir=`dirname $test`
        install -d ${D}${PTEST_PATH}/$testdir
        install -m 0755 ${B}/src/$test ${D}${PTEST_PATH}/$test
        if [ -d "${S}/$testdir/testdata" ]; then
            cp --preserve=mode,timestamps -R "${S}/$testdir/testdata" ${D}${PTEST_PATH}/$testdir
        fi
    done < ${B}/.go_compiled_tests.list
    if [ -n "$tests" ]; then
        install -d ${D}${PTEST_PATH}
        cat >${D}${PTEST_PATH}/run-ptest <<EOF
#!/bin/sh
ANYFAILED=0
for t in $tests; do
    testdir=\`dirname \$t.test\`
    if ( cd "${PTEST_PATH}/\$testdir"; "${PTEST_PATH}/\$t.test" ${GOPTESTFLAGS} | tee /dev/fd/9 | grep -q "^FAIL" ) 9>&1; then
        ANYFAILED=1
    fi
done
if [ \$ANYFAILED -ne 0 ]; then
    echo "FAIL: ${PN}"
    exit 1
fi
echo "PASS: ${PN}"
exit 0
EOF
        chmod +x ${D}${PTEST_PATH}/run-ptest
    else
        rm -rf ${D}${PTEST_PATH}
    fi
}

PACKAGES = "${@bb.utils.contains('PTEST_ENABLED', '1', '${PN}-ptest', '', d)} ${PN} ${PN}-dev"
PACKAGES_DYNAMIC = "^${PN}-lib.*"
FILES_${PN} = "${bindir}/* ${sysconfdir} ${sharedstatedir} \
               ${localstatedir} ${datadir}/${BPN}"
INSANE_SKIP_${PN} = "already-stripped ldflags dev-so textrel"
INSANE_SKIP_${PN}-ptest = "already-stripped ldflags textrel"
ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev = "${libdir}"
INSANE_SKIP_${PN}-dev = "staticdev"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

python populate_packages_prepend() {
    libdir = d.expand("${libdir}/go/pkg/${TARGET_GOTUPLE}_dynlink")
    pnbase = d.expand("${PN}-lib%s")
    do_split_packages(d, libdir, '^lib(.*)' + '\\' + d.expand("${SOLIBSDEV}"), pnbase,
                      'go library %s', prepend=True, extra_depends='')
}

EXPORT_FUNCTIONS do_unpack do_configure do_compile do_install
