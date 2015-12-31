SUMMARY = "Query language for JSON in go"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=9abfa8353fce3f2cb28364e1e9016852"
GO_SRCROOT = "github.com/jmespath/go-jmespath"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-smartystreets-goconvey"

inherit golang

PTEST_ENABLED = "0"
