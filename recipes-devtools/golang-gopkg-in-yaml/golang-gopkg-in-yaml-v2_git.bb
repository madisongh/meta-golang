SUMMARY = "YAML package for go"
HOMEPAGE = "https://github.com/go-yaml"
LICENSE = "Apachev2 & MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=6964839e54f4fefcdae13f22b92d0fbb \
                    file://${GO_SRCROOT}/LICENSE.libyaml;md5=d0b68be4a2dc957aaf09144970bc6696"
GO_SRCROOT = "gopkg.in/yaml.v2"
SRC_URI = "git://${GO_SRCROOT};protocol=https"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang
