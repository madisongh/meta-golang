require golang-common.inc

PV = "1.8rc3"
GO_BASEVERSION = "1.8"
FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/golang-${GO_BASEVERSION}:"

SRC_URI += "\
	file://0001-make.bash-fix-target-CC-for-build.patch \
	file://0002-Fix-CC-and-CXX-environment-variable-construction.patch \
	file://0003-make.bash-better-separate-host-and-target-builds.patch \
	file://0004-Allow-GOTOOLDIR-to-be-overridden-in-the-environment.patch \
	file://0005-Never-rebuild-already-installed-packages-in-GOROOT.patch \
	file://0006-linker-add-soname-to-shareable-objects.patch \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI[main.md5sum] = "c9a4acb510b2a66070e1b8bf7465abdb"
SRC_URI[main.sha256sum] = "38b1c1738f111f7bccdd372efca2aa98a7bad1ca2cb21767ba69f34ae007499c"
