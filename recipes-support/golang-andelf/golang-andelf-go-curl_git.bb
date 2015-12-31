SUMMARY = "Go bindings for libcurl"
GO_SRCROOT = "github.com/andelf/go-curl"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=36151766faff1b6cc8c21525002cddfa"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

DEPENDS = "curl"

do_configure_prepend() {
    rm -rf ${S}/${GO_SRCROOT}/examples
}

inherit golang pkgconfig
