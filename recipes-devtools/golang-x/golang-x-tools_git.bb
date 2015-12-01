SUMMARY = "Go tools"
HOMEPAGE = "https://godoc.org/golang.org/x/tools"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${GO_SRCROOT}/LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI = "git://github.com/golang/tools"
SRCREV = "${AUTOREV}"
PV="1.0+git${SRCPV}"

GO_SRCROOT = "golang.org/x/tools"

# Limiting the list of packages built to those that
# don't import google appengine and other large packages
GO_PACKAGES = " \
    golang.org/x/tools/benchmark/parse \
    golang.org/x/tools/blog \
    golang.org/x/tools/blog/atom \
    golang.org/x/tools/cmd/benchcmp \
    golang.org/x/tools/cmd/bundle \
    golang.org/x/tools/cmd/callgraph \
    golang.org/x/tools/cmd/cover \
    golang.org/x/tools/cmd/digraph \
    golang.org/x/tools/cmd/eg \
    golang.org/x/tools/cmd/fiximports \
    golang.org/x/tools/cmd/godex \
    golang.org/x/tools/cmd/godoc \
    golang.org/x/tools/cmd/goimports \
    golang.org/x/tools/cmd/gomvpkg \
    golang.org/x/tools/cmd/gorename \
    golang.org/x/tools/cmd/gotype \
    golang.org/x/tools/cmd/html2article \
    golang.org/x/tools/cmd/oracle \
    golang.org/x/tools/cmd/present \
    golang.org/x/tools/cmd/ssadump \
    golang.org/x/tools/cmd/stress \
    golang.org/x/tools/cmd/stringer \
    golang.org/x/tools/cmd/tip \
    golang.org/x/tools/cmd/vet \
    golang.org/x/tools/cmd/vet/whitelist \
    golang.org/x/tools/container/intsets \
    golang.org/x/tools/cover \
    golang.org/x/tools/go/ast/astutil \
    golang.org/x/tools/go/buildutil \
    golang.org/x/tools/go/callgraph \
    golang.org/x/tools/go/callgraph/cha \
    golang.org/x/tools/go/callgraph/rta \
    golang.org/x/tools/go/callgraph/static \
    golang.org/x/tools/go/exact \
    golang.org/x/tools/go/gccgoimporter \
    golang.org/x/tools/go/gcimporter \
    golang.org/x/tools/go/importer \
    golang.org/x/tools/go/loader \
    golang.org/x/tools/go/pointer \
    golang.org/x/tools/go/ssa \
    golang.org/x/tools/go/ssa/interp \
    golang.org/x/tools/go/ssa/ssautil \
    golang.org/x/tools/go/types \
    golang.org/x/tools/go/types/typeutil \
    golang.org/x/tools/go/vcs \
    golang.org/x/tools/godoc \
    golang.org/x/tools/godoc/analysis \
    golang.org/x/tools/godoc/redirect \
    golang.org/x/tools/godoc/static \
    golang.org/x/tools/godoc/util \
    golang.org/x/tools/godoc/vfs \
    golang.org/x/tools/godoc/vfs/gatefs \
    golang.org/x/tools/godoc/vfs/httpfs \
    golang.org/x/tools/godoc/vfs/mapfs \
    golang.org/x/tools/godoc/vfs/zipfs \
    golang.org/x/tools/imports \
    golang.org/x/tools/oracle \
    golang.org/x/tools/oracle/serial \
    golang.org/x/tools/playground \
    golang.org/x/tools/playground/socket \
    golang.org/x/tools/present \
    golang.org/x/tools/refactor/eg \
    golang.org/x/tools/refactor/importgraph \
    golang.org/x/tools/refactor/rename \
    golang.org/x/tools/refactor/satisfy \
"

inherit golang

DEPENDS += "golang-x-net"
RDEPENDS_${PN}-dev = "bash"

BBCLASSEXTEND = "native"
