SUMMARY = "Go package for prettifying terminal/console output"
GO_SRCROOT = "github.com/ttacon/chalk"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=bae6fad352ff83e3081f79f362983d2f"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

# Don't build the examples
GO_PACKAGES = "github.com/ttacon/chalk"

inherit golang

BBCLASSEXTEND = "native"
