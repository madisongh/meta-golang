require golang-common.inc

DEFAULT_PREFERENCE = "-1"
PV = "1.9.2"
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
SRC_URI[main.md5sum] = "44105c865a1a810464df79233a05a568"
SRC_URI[main.sha256sum] = "665f184bf8ac89986cfd5a4460736976f60b57df6b320ad71ad4cef53bb143dc"
