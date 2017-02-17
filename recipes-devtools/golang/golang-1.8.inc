require golang-common.inc

PV = "1.8"
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
SRC_URI[main.md5sum] = "7743960c968760437b6e39093cfe6f67"
SRC_URI[main.sha256sum] = "406865f587b44be7092f206d73fc1de252600b79b3cacc587b74b5ef5c623596"
