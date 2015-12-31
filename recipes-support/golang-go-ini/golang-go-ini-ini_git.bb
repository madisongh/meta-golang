SUMMARY = ".ini parser for go"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=19cbd64715b51267a47bf3750cc6a8a5"
GO_SRCROOT = "github.com/go-ini/ini"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang

PTEST_ENABLED = "0"
