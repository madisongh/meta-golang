SUMMARY = "Go support for making OAuth2 authorized and autheticated HTTP requests"
HOMEPAGE = "https://godoc.org/golang.org/x/oauth2"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=704b1e0c436dbf193e7dcbd4cf06ec81"
SRC_URI = "git://github.com/golang/oauth2;name=oauth2 \
           git://github.com/GoogleCloudPlatform/gcloud-golang;name=cloud;destsuffix=${GO_SRC_PARENT}/vendor/google.golang.org/cloud \
           git://github.com/grpc/grpc-go;name=grpc;destsuffix=${GO_SRC_PARENT}/vendor/google.golang.org/grpc"
SRCREV_oauth2 = "${AUTOREV}"
SRCREV_cloud = "${AUTOREV}"
SRCREV_grpc = "${AUTOREV}"
SRCREV_FORMAT = "oauth2+cloud+grpc"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/oauth2"

DEPENDS = "golang-x-net"

inherit golang

# Tests require the google appengine package
PTEST_ENABLED = "0"
