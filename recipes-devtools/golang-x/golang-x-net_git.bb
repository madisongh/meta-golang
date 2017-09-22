SUMMARY = "Go supplementary network support"
HOMEPAGE = "https://github.com/golang/net"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/net"

require golang-x-srcrevs.inc

SRCREV = "${GOLANG_X_NET_SRCREV}"
PV="1.0+git${SRCPV}"

GO_IMPORT = "golang.org/x/net"
S = "${WORKDIR}/git"

DEPENDS = "golang-x-text golang-x-crypto"

inherit go

PTEST_ENABLED = ""

BBCLASSEXTEND = "native nativesdk"
