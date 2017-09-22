SUMMARY = "Go support for protocol buffers"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=14db3a56c3796a940ba32948a15f97d0"
SRC_URI = "git://${GO_IMPORT}"
SRCREV ?= "98fa357170587e470c5f27d3c3ea0947b71eb455"
PV="1.0+git${SRCPV}"

S = "${WORKDIR}/git"
GO_IMPORT = "github.com/golang/protobuf"

DEPENDS = "golang-x-net"

inherit go

RDEPENDS_${PN}-dev += "bash"

BBCLASSEXTEND = "native nativesdk"
