SUMMARY = "Goroutine thread-local storage package" 
HOMEPAGE = "http://godoc.org/github.com/jtolds/gls"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=3a8fb9d19728802d1a417380666b3ae6"
GO_SRCROOT = "github.com/jtolds/gls"
SRC_URI = "git://${GO_SRCROOT}"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

inherit golang
