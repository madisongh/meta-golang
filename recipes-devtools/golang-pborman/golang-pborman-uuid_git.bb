SUMMARY = "UUID generation and inspection for Go"
GO_SRCROOT = "github.com/pborman/uuid"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=88073b6dd8ec00fe09da59e0b6dfded1"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang

