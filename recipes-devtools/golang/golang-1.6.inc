require golang-common.inc

PV = "1.6rc1"
GO_BASEVERSION = "1.6"
FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/golang-${GO_BASEVERSION}:"

SRC_URI = "git://github.com/golang/go;branch=${SRCBRANCH}"
SRCBRANCH ?= "release-branch.go1.6"
SRCREV ?= "036b8fd40b60830ca1d152f17148e52b96d8aa6c"

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

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=591778525c869cdde0ab5a1bf283cd81"
