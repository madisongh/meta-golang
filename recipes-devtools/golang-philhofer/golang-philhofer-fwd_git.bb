SUMMARY = "Buffered reader and writer package for Go"
GO_SRCROOT = "github.com/philhofer/fwd"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE.md;md5=cfd452e4c91aa5191e3af8f3e4b67a9a"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang

BBCLASSEXTEND = "native"
