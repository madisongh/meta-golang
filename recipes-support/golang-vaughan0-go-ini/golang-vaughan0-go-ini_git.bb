SUMMARY = ".ini parser for go"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=ca72ab4b03e1d14c7d09d9329121ef77"
GO_SRCROOT = "github.com/vaughan0/go-ini"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang
