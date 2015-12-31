SUMMARY = "Go testing tool with web UI" 
HOMEPAGE = "http://goconvey.co"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE.md;md5=332cd9cbee8d58f5f2f5eb8db06d9e72 \
                    file://${GO_SRCROOT}/vendor/github.com/smartystreets/assertions/LICENSE.md;md5=463753b1c52cffde85983f6ab21b71f5"
GO_SRCROOT = "github.com/smartystreets/goconvey"
SRC_URI = "git://${GO_SRCROOT};name=goconvey \
           git://github.com/smartystreets/assertions;name=assertions;destsuffix=${GO_SRCROOT}/vendor/github.com/smartystreets/assertions"
SRCREV_goconvey = "${AUTOREV}"
SRCREV_assertions = "${AUTOREV}"
SRCREV_FORMAT = "goconvey+assertions"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-x-net golang-jtolds-gls"

export GO15VENDOREXPERIMENT = "1"

inherit golang

PTEST_ENABLED = "0"
