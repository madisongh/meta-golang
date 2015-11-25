SUMMARY = "Testing extensions for go"
HOMEPAGE = "http://labix.org/gocheck"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=598d6673548efc92a7d6dfb739add1ed"
GO_SRCROOT = "gopkg.in/check.v1"
SRC_URI = "git://${GO_SRCROOT};protocol=https"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang
