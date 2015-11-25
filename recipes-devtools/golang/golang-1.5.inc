require golang-common.inc

PV = "1.5.1"
GO_BASEVERSION = "1.5"
FILESEXTRAPATHS_prepend := "${FILE_DIRNAME}/golang-${GO_BASEVERSION}:"


SRC_URI += "\
       file://armhf-elf-header.patch \
       file://syslog.patch \
       file://fix-target-cc-for-build.patch \
       file://fix-cc-handling.patch \
       file://split-host-and-target-build.patch \
       file://gotooldir.patch \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=591778525c869cdde0ab5a1bf283cd81"
SRC_URI[md5sum] = "4adfbdfca523cc1c229be8a321f3602f"
SRC_URI[sha256sum] = "a889873e98d9a72ae396a9b7dd597c29dcd709cafa9097d9c4ba04cff0ec436b"
