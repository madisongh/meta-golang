require golang-common.inc

PV = "1.7rc2"
GO_BASEVERSION = "1.7"
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

LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI[main.md5sum] = "c5cd29076e50111d299c2411ded5b427"
SRC_URI[main.sha256sum] = "87bafefb093dd163d264099b39b1bcdc227f54f935b77f5ff74b0d57e3638da6"
