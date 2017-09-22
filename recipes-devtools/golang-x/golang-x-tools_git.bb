SUMMARY = "Go tools (subset)"
HOMEPAGE = "https://godoc.org/golang.org/x/tools"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/tools;name=tools \
           git://github.com/golang/net;name=net;destsuffix=git/src/${GO_IMPORT}/vendor/golang.org/x/net"

require golang-x-srcrevs.inc

SRCREV_tools = "${GOLANG_X_TOOLS_SRCREV}"
SRCREV_net = "${GOLANG_X_NET_SRCREV}"
SRCREV_FORMAT = "tools+net"
PV="1.0+git${SRCPV}"

GO_IMPORT = "golang.org/x/tools"
S = "${WORKDIR}/git"
DEPENDS = "golang-x-crypto"

inherit go

PTEST_ENABLED = ""

RDEPENDS_${PN}-dev = "bash"
INSANE_SKIP_${PN}-dev += "arch"

BBCLASSEXTEND = "native"
