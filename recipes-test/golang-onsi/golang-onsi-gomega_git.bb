SUMMARY = "Matcher library for use with ginkgo"
HOMEPAGE = "http://onsi.github.io/gomega"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=570603114d52313cb86c0206401c9af7"
GO_SRCROOT = "github.com/onsi/gomega"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

DEPENDS = "golang-goprotobuf"

inherit golang

PTEST_ENABLED = "0"
