SUMMARY = "Go text processing libraries"
HOMEPAGE = "https://godoc.org/golang.org/x/text"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707 \
                    file://${GO_SRC_PARENT}/vendor/golang/x/tools/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/text;name=text \
           git://github.com/golang/tools;name=tools;destsuffix=${GO_SRC_PARENT}/vendor/golang.org/x/tools"
SRCREV_text ?= "${AUTOREV}"
SRCREV_tools ?= "${AUTOREV}"
SRCREV_FORMAT = "text+tools"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/text"
CGO_ENABLED = "1"

inherit golang

PTEST_ENABLED = ""

BBCLASSEXTEND = "native nativesdk"
