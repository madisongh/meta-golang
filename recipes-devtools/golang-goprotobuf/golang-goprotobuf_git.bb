SUMMARY = "Go support for protocol buffers"
GO_SRCROOT = "github.com/golang/protobuf"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=14db3a56c3796a940ba32948a15f97d0"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV ?= "98fa357170587e470c5f27d3c3ea0947b71eb455"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-x-net"

inherit golang

do_configure_prepend() {
    rm -f ${S}/${GO_SRCROOT}/_conformance/_conformance
}

RDEPENDS_${PN}-dev += "bash"

BBCLASSEXTEND = "native nativesdk"
