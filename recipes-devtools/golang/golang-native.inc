inherit native

SRC_URI_append = " http://golang.org/dl/go1.4.2.src.tar.gz;name=bootstrap;subdir=go1.4"
SRC_URI[bootstrap.md5sum] = "907f85c8fa765d31f7f955836fec4049"
SRC_URI[bootstrap.sha256sum] = "299a6fd8f8adfdce15bc06bde926e7b252ae8e24dd5b16b7d8791ed79e7b5e9b"


export GOOS = "${BUILD_GOOS}"
export GOARCH = "${BUILD_GOARCH}"
CC = "${@d.getVar('BUILD_CC', True).strip()}"

export CGO_ENABLED = "1"

do_configure() {
    cd ${WORKDIR}/go1.4/go/src
    GOROOT=${WORKDIR}/go1.4/go ./make.bash
}

do_compile() {
    export GOROOT_FINAL="${STAGING_LIBDIR_NATIVE}/go"
    export GOROOT_BOOTSTRAP="${WORKDIR}/go1.4/go"
    export GOBIN="${B}/bin"
    rm -rf ${GOBIN}
    mkdir ${GOBIN}
    cd src
    ./make.bash --host-only
}


make_wrapper() {
    rm -f ${D}${bindir}/$2
    cat <<END >${D}${bindir}/$2
#!/bin/bash
here=\`dirname \$0\`
export GOROOT="${GOROOT:-\`readlink -f \$here/../lib/go\`}"
\$here/../lib/go/bin/$1 "\$@"
END
    chmod +x ${D}${bindir}/$2
}

do_install() {
    install -d ${D}${libdir}/go
    cp -a ${B}/pkg ${D}${libdir}/go/
    install -d ${D}${libdir}/go/src
    (cd ${S}/src; for d in *; do \
        [ -d $d ] && cp -a ${S}/src/$d ${D}${libdir}/go/src/; \
    done)
    install -d ${D}${bindir} ${D}${libdir}/go/bin
    for f in ${B}/bin/*
    do
        base=`basename $f`
        install -m755 $f ${D}${libdir}/go/bin
        make_wrapper $base $base
    done
}

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_rpm[noexec] = "1"
