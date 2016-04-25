require golang-common.inc

PV = "1.5.4"
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
SRC_URI[main.md5sum] = "a04d570515c46e4935c63605cbd3a04e"
SRC_URI[main.sha256sum] = "002acabce7ddc140d0d55891f9d4fcfbdd806b9332fb8b110c91bc91afb0bc93"
