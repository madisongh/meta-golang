require golang-common.inc

PV = "1.6"
GO_BASEVERSION = "1.6"
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
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=591778525c869cdde0ab5a1bf283cd81"
SRC_URI[main.md5sum] = "e67833ea37fbc002fbe38efe6c1bcd98"
SRC_URI[main.sha256sum] = "a96cce8ce43a9bf9b2a4c7d470bc7ee0cb00410da815980681c8353218dcf146"
