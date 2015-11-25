SUMMARY = "Go package for dealing with maps, slices, JSON, and other data"
HOMEPAGE = "http://godoc.org/github.com/stretchr/objx"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE.md;md5=21e79cfe7201b9b64535bfae0895795b"
GO_SRCROOT = "github.com/stretchr/objx"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-stretchr-testify"

inherit golang

