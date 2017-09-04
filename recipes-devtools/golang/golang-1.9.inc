require golang-common.inc

DEFAULT_PREFERENCE = "-1"
PV = "1.9"
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
SRC_URI[main.md5sum] = "da2d44ea384076efec43ee1f8b7d45d2"
SRC_URI[main.sha256sum] = "a4ab229028ed167ba1986825751463605264e44868362ca8e7accc8be057e993"
