require golang-common.inc

PV = "1.9.1"
GO_BASEVERSION = "1.9"
FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/golang-${GO_BASEVERSION}:"

SRC_URI += "\
	file://0001-make.bash-separate-host-and-target-builds.patch \
	file://0002-Fix-CC-and-CXX-environment-variable-construction.patch \
	file://0003-Allow-GOTOOLDIR-to-be-overridden-in-the-environment.patch \
	file://0004-Never-rebuild-already-installed-packages-in-GOROOT.patch \
	file://0005-linker-add-soname-to-shareable-objects.patch \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI[main.md5sum] = "27bce1ffb05f4f6bd90d90081e5d4169"
SRC_URI[main.sha256sum] = "a84afc9dc7d64fe0fa84d4d735e2ece23831a22117b50dafc75c1484f1cb550e"
