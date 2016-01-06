DEPENDS = "virtual/${TARGET_PREFIX}golang golang-native"
DEPENDS_class-nativesdk = "virtual/${TARGET_PREFIX}golang-crosssdk golang-native"

BBCLASSEXTEND = "nativesdk"

export GOHOSTOS = "${BUILD_GOOS}"
export GOHOSTARCH = "${BUILD_GOARCH}"
export GOOS = "${TARGET_GOOS}"
export GOARCH = "${TARGET_GOARCH}"
export GOARM = "${TARGET_GOARM}"
export GOROOT_BOOTSTRAP = "${STAGING_LIBDIR_NATIVE}/go"
export GOROOT_FINAL = "${libdir}/go"
export CGO_ENABLED = "1"
export CC_FOR_TARGET="${CC}"
export CXX_FOR_TARGET="${CXX}"

do_configure() {
    :
}
do_configure[noexec] = "1"

do_compile() {
    export GOBIN="${B}/bin"
    rm -rf ${GOBIN} ${B}/pkg
    mkdir ${GOBIN}
    cd src
    CC="${@d.getVar('BUILD_CC',True).strip()}" ./make.bash
}

do_install() {
    install -d ${D}${libdir}/go
    install -d ${D}${libdir}/go/bin
    install -d ${D}${libdir}/go/pkg/tool
    install -d ${D}${bindir}
    cp --preserve=mode,timestamps -R ${S}/pkg/tool/${TARGET_GOTUPLE} ${D}${libdir}/go/pkg/tool/
    for f in ${B}/${GO_BUILD_BINDIR}/*
    do
        base=`basename $f`
        install -m755 $f ${D}${libdir}/go/bin
	ln -sf ../lib/go/bin/$base ${D}${bindir}/
    done
}

PACKAGES = "${PN} ${PN}-dev"
FILES_${PN} = "${libdir}/go/bin ${libdir}/go/pkg/tool/${GOOS}_${GOARCH} ${bindir}"
FILES_${PN}-dev = "${libdir}/go"
RDEPENDS_${PN}-dev += "bash"
INSANE_SKIP_${PN} = "already-stripped ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
