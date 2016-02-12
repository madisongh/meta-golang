require golang-common.inc

PV = "1.5.3"
GO_BASEVERSION = "1.5"
FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/golang-${GO_BASEVERSION}:"


SRC_URI += "\
       file://armhf-elf-header.patch \
       file://syslog.patch \
       file://fix-target-cc-for-build.patch \
       file://fix-cc-handling.patch \
       file://split-host-and-target-build.patch \
       file://gotooldir.patch \
       file://do-not-rebuild-installed-pkgs.patch \
       file://gotooldir-cross-canadian.patch \
       file://linker-insert-soname.patch \
       file://linker-copyn-workaround.patch \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=591778525c869cdde0ab5a1bf283cd81"
SRC_URI[main.md5sum] = "80a0eac7ab750b01b3f7096a1d4667b8"
SRC_URI[main.sha256sum] = "754e06dab1c31ab168fc9db9e32596734015ea9e24bc44cae7f237f417ce4efe"
