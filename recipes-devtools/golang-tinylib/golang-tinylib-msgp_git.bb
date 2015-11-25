SUMMARY = "Go code generator for messagepack"
GO_SRCROOT = "github.com/tinylib/msgp"
HOMEPAGE = "https://${GO_SRCROOT}"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=37acb030ba070680be4a9fcb57f2735a"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

NATIVEDEPENDS = "golang-tinylib-msgp-native"
NATIVEDEPENDS_class-native = ""
DEPENDS += "golang-philhofer-fwd golang-ttacon-chalk golang-x-tools ${NATIVEDEPENDS}"


do_compile_ptest_base_prepend() {
    ${GO} generate ${GO_SRCROOT}/msgp
}

# For native builds, 'msgp' isn't installed in the sysroot yet
do_compile_ptest_base_prepend_class_native() {
    PATH=${B}/bin:${PATH}
}

inherit golang

BBCLASSEXTEND = "native"
