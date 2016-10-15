SUMMARY = "Extensions to the standard go testing package"
HOMEPAGE = "http://godoc.org/github.com/stretchr/testify"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENCE.txt;md5=39cd1d751bc25944831de86496e3cf68"
GO_SRCROOT = "github.com/stretchr/testify"
SRC_URI = "git://${GO_SRCROOT};name=testify \
           git://github.com/stretchr/objx;name=objx;destsuffix=${GO_SRC_PARENT}/vendor/github.com/stretchr/objx"
SRCREV_testify = "${AUTOREV}"
SRCREV_objx = "${AUTOREV}"
SRCREV_FORMAT = "testify+objx"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-davecgh-go-spew golang-pmezard-go-difflib"

inherit golang

