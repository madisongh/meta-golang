SUMMARY = "Go testing tool with web UI" 
HOMEPAGE = "http://goconvey.co"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE.md;md5=4f3d0cac23eaa070c40e054fad4e78a8 \
                    file://${GO_SRC_PARENT}/vendor/github.com/smartystreets/assertions/LICENSE.md;md5=019e27fed5ea0aeabe798d69cb314a67"
GO_SRCROOT = "github.com/smartystreets/goconvey"
SRC_URI = "git://${GO_SRCROOT};name=goconvey \
           git://github.com/smartystreets/assertions;name=assertions;destsuffix=${GO_SRC_PARENT}/vendor/github.com/smartystreets/assertions"
SRCREV_goconvey = "${AUTOREV}"
SRCREV_assertions = "${AUTOREV}"
SRCREV_FORMAT = "goconvey+assertions"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-x-net golang-jtolds-gls"

inherit golang

PTEST_ENABLED = "0"
