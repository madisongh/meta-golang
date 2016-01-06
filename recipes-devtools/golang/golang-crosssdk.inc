inherit crosssdk

DEPENDS += "golang-native virtual/${TARGET_PREFIX}gcc-crosssdk virtual/nativesdk-${TARGET_PREFIX}compilerlibs virtual/${TARGET_PREFIX}binutils-crosssdk"
PN = "golang-crosssdk-${TARGET_ARCH}"
PROVIDES = "virtual/${TARGET_PREFIX}golang-crosssdk"

export GOHOSTOS = "${BUILD_GOOS}"
export GOHOSTARCH = "${BUILD_GOARCH}"
export GOOS = "${TARGET_GOOS}"
export GOARCH = "${TARGET_GOARCH}"
export GOROOT_BOOTSTRAP = "${STAGING_LIBDIR_NATIVE}/go"
export GOROOT_FINAL = "${libdir}/go"
export CGO_ENABLED = "1"
export CC_FOR_TARGET="${TARGET_PREFIX}gcc ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"
export CXX_FOR_TARGET="${TARGET_PREFIX}g++ ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"
CC = "${@d.getVar('BUILD_CC', True).strip()}"

do_configure() {
    :
}

do_compile() {
    export GOBIN="${B}/bin"
    rm -rf ${GOBIN} ${B}/pkg
    mkdir ${GOBIN}
    cd src
    ./make.bash --host-only
}

make_wrapper() {
    rm -f ${D}${bindir}/$2
    cat <<END >${D}${bindir}/$2
#!/bin/bash
here=\`dirname \$0\`
export GOARCH="${TARGET_GOARCH}"
export GOOS="${TARGET_GOOS}"
export GOARM="${TARGET_GOARM}"
\$here/../../lib/${CROSS_TARGET_SYS_DIR}/go/bin/$1 "\$@"
END
    chmod +x ${D}${bindir}/$2
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
	make_wrapper $base ${TARGET_PREFIX}$base
    done
}

INSANE_SKIP_${PN} = "staticdev"
