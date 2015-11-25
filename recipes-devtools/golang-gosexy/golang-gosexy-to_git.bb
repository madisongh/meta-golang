SUMMARY = "Go Bindings for gosexy's to library."
HOMEPAGE = "https://github.com/gosexy/to"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=bbb75af21c15f071568e28a0b643154e"
SRC_URI = "git://github.com/gosexy/to"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "menteslibres.net/gosexy/to"

inherit golang
