SUMMARY = "Go supplementary concurrency support"
HOMEPAGE = "https://github.com/golang/sync"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/sync"

require golang-x-srcrevs.inc

SRCREV = "${GOLANG_X_SYNC_SRCREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/sync"

DEPENDS = "golang-x-net"

inherit golang

PTEST_ENABLED = ""
