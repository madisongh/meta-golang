SUMMARY = "Go supplementary network support"
HOMEPAGE = "https://github.com/golang/net"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/net"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/net"

DEPENDS = "golang-x-text golang-x-crypto"

inherit golang

BBCLASSEXTEND = "native nativesdk"
