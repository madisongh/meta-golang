SUMMARY = "Go tools (subset)"
HOMEPAGE = "https://godoc.org/golang.org/x/tools"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/tools"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/tools"

do_configure_prepend() {
    rm -rf ${S}/${GO_SRCROOT}/cmd/bundle
    rm -rf ${S}/${GO_SRCROOT}/godoc/dl
    rm -rf ${S}/${GO_SRCROOT}/godoc/proxy
    rm -rf ${S}/${GO_SRCROOT}/godoc/short
    if [ -f ${S}/${GO_SRCROOT}/go/gcimporter15/gcimporter_test16.go ]; then
        (cd ${S}/${GO_SRCROOT}/go/gcimporter15; \
	  mv gcimporter_test16.go gcimporter16_test.go)
    fi
}

inherit golang

PTEST_ENABLED = ""

DEPENDS += "golang-x-net"
RDEPENDS_${PN}-dev = "bash"

BBCLASSEXTEND = "native"
