DEPENDS = "virtual/${TARGET_PREFIX}golang golang-native"
DEPENDS_class-nativesdk = "virtual/${TARGET_PREFIX}golang-crosssdk"
PROVIDES = "virtual/${TARGET_PREFIX}golang-runtime"

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
    install -d ${D}${libdir}/go/src
    cp -a ${B}/pkg ${D}${libdir}/go/
    if [ "${BUILD_GOTUPLE}" != "${TARGET_GOTUPLE}" ]; then
        rm -rf ${D}${libdir}/go/pkg/${BUILD_GOTUPLE}
        rm -rf ${D}${libdir}/go/pkg/obj/${BUILD_GOTUPLE}
    fi
    rm -rf ${D}${libdir}/go/pkg/tool
    rm -rf ${D}${libdir}/go/pkg/bootstrap    
    find src -mindepth 1 -maxdepth 1 -type d | while read srcdir; do
	cp -av $srcdir ${D}${libdir}/go/src/
    done
}

PACKAGES = "${PN} ${PN}-dev"
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = "${libdir}/go/pkg/${TARGET_GOTUPLE}/*${SOLIBSDEV} \
	       ${libdir}/go/pkg/${TARGET_GOTUPLE}/*/*${SOLIBSDEV} \
               ${libdir}/go/pkg/${TARGET_GOTUPLE}/*/*/*${SOLIBSDEV} \
               ${libdir}/go/pkg/${TARGET_GOTUPLE}/*/*/*${SOLIBSDEV} \
"
FILES_${PN}-dev = "${libdir}/go"
INSANE_SKIP_${PN} = "already-stripped ldflags"
INSANE_SKIP_${PN}-dev = "ldflags staticdev file-rdeps arch"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
