DESCRIPTION = "Go supplementary packages for\
 low-level interactions with the operating system"
HOMEPAGE = "https://github.com/golang/sys"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/sys"

require golang-x-srcrevs.inc

SRCREV = "${GOLANG_X_SYS_SRCREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/sys"
GO_INSTALL = "${GO_SRCROOT}/unix"

inherit golang

PTEST_ENABLED = ""

BBCLASSEXTEND = "native nativesdk"
