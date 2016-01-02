SUMMARY = "AWS SDK for Go (subset)"
HOMEPAGE = "http://aws.amazon.com/sdk-for-go/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"
GO_SRCROOT = "github.com/aws/aws-sdk-go"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

export GO15VENDOREXPERIMENT = "1"

GO_BUILD_SHLIBS = "0"

DEPENDS = "golang-stretchr-testify golang-go-ini-ini golang-jmespath-go-jmespath"

inherit golang

do_configure_prepend() {
    rm -rf ${S}/${GO_SRCROOT}/awstesting/integration
    rm -rf ${S}/${GO_SRCROOT}/awsmigrate
    rm -rf ${S}/${GO_SRCROOT}/example
}
