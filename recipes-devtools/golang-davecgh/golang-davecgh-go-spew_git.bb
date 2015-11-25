SUMMARY = "Implements a deep pretty printer for Go data structures"
HOMEPAGE = "http://godoc.org/github.com/davecgh/go-spew/spew"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=8bc888171b6c073957745dfa153bd402"
GO_SRCROOT = "github.com/davecgh/go-spew"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang

