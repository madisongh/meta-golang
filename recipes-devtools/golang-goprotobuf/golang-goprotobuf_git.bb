SUMMARY = "Go support for protocol buffers"
GO_SRCROOT = "github.com/golang/protobuf"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=14db3a56c3796a940ba32948a15f97d0"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-x-net"

inherit golang

BBCLASSEXTEND = "native nativesdk"
