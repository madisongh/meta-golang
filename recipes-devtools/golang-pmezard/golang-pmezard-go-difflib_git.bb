SUMMARY = "Partial port of Python difflib package to Go"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=e9a2ebb8de779a07500ddecca806145e"
GO_SRCROOT = "github.com/pmezard/go-difflib"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang

