inherit golang-osarchmap ptest

DEPENDS_GOLANG_class-target = "virtual/${TARGET_PREFIX}golang virtual/${TARGET_PREFIX}golang-runtime"
DEPENDS_GOLANG_class-nativesdk = "virtual/${TARGET_PREFIX}golang-crosssdk virtual/${TARGET_PREFIX}golang-runtime"
DEPENDS_GOLANG_class-native = "golang-native"

DEPENDS_append = " ${DEPENDS_GOLANG}"

export GO = "${HOST_PREFIX}go"
export GOPATH = "${B}"
export GOBUILDFLAGS ?= "-x -v"
export GOPTESTBUILDFLAGS ?= "${GOBUILDFLAGS} -c"
export GOPTESTFLAGS ?= "-test.v"

GO_BUILDBIN = "${B}/${GO_BUILD_BINDIR}"
GO_BUILDBIN_class-native = "${B}/bin"

S_GOROOT = "${WORKDIR}/go"
S = "${S_GOROOT}/src"
GO_SRC_PARENT = "${@os.path.dirname(d.getVar('GO_SRCROOT', True))}"
B = "${WORKDIR}/build"
GO_TMPDIR = "${WORKDIR}/go-tmp"

GOROOT = "${STAGING_LIBDIR}/go"
GOROOT_class-native = "${STAGING_LIBDIR_NATIVE}/go"
GOROOT_class-nativesdk = "${STAGING_DIR_TARGET}${libdir}/go"
export GOROOT
export GOTOOLDIR = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/go/pkg/tool/${BUILD_GOTUPLE}"

GO_PACKAGES ?= "all"

export CGO_ENABLED ?= "0"

do_unpack[dirs] = "${S_GOROOT}/src"
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

do_configure[dirs] = "${B}"
do_configure[cleandirs] = "${B}"
golang_do_configure() {
    mkdir -p ${B}/src/${GO_SRC_PARENT}
    ln -snf ${S}/${GO_SRCROOT} ${B}/src/${GO_SRC_PARENT}/
    cd ${S_GOROOT}
    rm -f ${B}/.go_compile.list ${B}/.go_compile_ptest.list
    export CGO_ENABLED=1
    GOPATH=${S_GOROOT} ${GO} list -f '{{if not .Goroot}}{{.ImportPath}} {{.CgoFiles}}{{end}}' ${GO_PACKAGES} | grep -v '${GO_SRCROOT}/vendor/' >${B}/.go_compile.list

    GOPATH=${S_GOROOT} ${GO} list -f '{{if not .Goroot}}{{.ImportPath}} {{.Dir}} {{.Root}} {{.CgoFiles}}{{end}}' ${GO_PACKAGES} | grep -v '${GO_SRCROOT}/vendor/' >${B}/.go_compile_ptest.list
    GOPATH=${S_GOROOT} ${GO} list -f '{{if not .Goroot}}{{.ImportPath}} {{.Root}} {{.Incomplete}} {{end}}' ${GO_PACKAGES} | grep -v '${GO_SRCROOT}/vendor/' | while read pkg root inc; do
        if [ "$root" != "${S_GOROOT}" ]; then
            bberror "${PN}: package $pkg root is outside source directory"
        fi
        if $inc; then
            bberror "${PN}: package $pkg is missing dependencies"
        fi
    done
}

do_compile[dirs] =+ "${B}/bin ${GO_TMPDIR}"
do_compile[cleandirs] = "${B}/bin ${GO_TMPDIR}"

golang_do_compile() {
    export TMPDIR="${GO_TMPDIR}"
    while read pkg cgofiles; do
        [ "$cgofiles" != "[]" ] && CGO_ENABLED=1
        ${GO} install ${GOBUILDFLAGS} $pkg
    done < ${B}/.go_compile.list
}

golang_do_install() {
    didbindir=""
    install -d ${D}${libdir}/go/src/${@os.path.dirname(d.getVar('GO_SRCROOT', True))}
    cp --preserve=mode,timestamps -R ${S} ${D}${libdir}/go/
    find ${D}${libdir}/go/src/${GO_SRCROOT} -type f -name '*.test' -exec rm {} \;
    for tgtfile in ${GO_BUILDBIN}/*; do
        [ ! -e $tgtfile ] && continue
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
    while read pkg pkgdir pkgroot cgofiles; do
        cd $pkgdir
        [ "$cgofiles" != "[]" ] && CGO_ENABLED=1
        ${GO} test ${GOPTESTBUILDFLAGS} $pkg
        relpath=`echo $pkgdir | cut -b ${@len(d.getVar('S_GOROOT', True) + "/src")+2}-`
        find . -mindepth 1 -maxdepth 1 -type f -name '*.test' -exec echo $relpath/{} \; | sed -e's,/\./,/,' >>${B}/.go_compiled_tests.list
    done < ${B}/.go_compile_ptest.list
    cd $curpwd
}

do_install_ptest_base() {
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
FILES_${PN} = "${bindir}/* ${sysconfdir} ${sharedstatedir} \
               ${localstatedir} ${datadir}/${BPN}"
INSANE_SKIP_${PN} = "already-stripped ldflags"
INSANE_SKIP_${PN}-ptest = "already-stripped ldflags"
ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev = "${libdir}"
INSANE_SKIP_${PN}-dev = "staticdev"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

EXPORT_FUNCTIONS do_unpack do_configure do_compile do_install
