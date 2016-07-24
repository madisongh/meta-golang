BUILD_GOOS = "${@golang_map_os(d.getVar('BUILD_OS', True), d)}"
BUILD_GOARCH = "${@golang_map_arch(d.getVar('BUILD_ARCH', True), d)}"
BUILD_GOTUPLE = "${BUILD_GOOS}_${BUILD_GOARCH}"
HOST_GOOS = "${@golang_map_os(d.getVar('HOST_OS', True), d)}"
HOST_GOARCH = "${@golang_map_arch(d.getVar('HOST_ARCH', True), d)}"
HOST_GOARM = "${@golang_map_arm(d.getVar('HOST_ARCH', True), d.getVar('TUNE_FEATURES', True), d)}"
HOST_GOTUPLE = "${HOST_GOOS}_${HOST_GOARCH}"
TARGET_GOOS = "${@golang_map_os(d.getVar('TARGET_OS', True), d)}"
TARGET_GOARCH = "${@golang_map_arch(d.getVar('TARGET_ARCH', True), d)}"
TARGET_GOARM = "${@golang_map_arm(d.getVar('TARGET_ARCH', True), d.getVar('TUNE_FEATURES', True), d)}"
TARGET_GOTUPLE = "${TARGET_GOOS}_${TARGET_GOARCH}"
GO_BUILD_BINDIR = "${@['bin/${HOST_GOTUPLE}','bin'][d.getVar('BUILD_GOTUPLE',True) == d.getVar('HOST_GOTUPLE',True)]}"
GO_SHLIBS_SUPPORTED ?= "${@golang_arch_supports_shlibs(d.getVar('TARGET_GOARCH', True), d)}"
GO_SHLIBS_SUPPORTED_class-native = "0"
GO_SHLIB_ARCHS ??= "amd64"

def golang_map_arch(a, d):
    import re
    if re.match('i.86', a):
        return '386'
    elif a == 'x86_64':
        return 'amd64'
    elif re.match('arm.*', a):
        return 'arm'
    elif re.match('aarch64.*', a):
        return 'arm64'
    else:
        bb.error("cannot map '%s' to a Go architecture" % a)

def golang_map_arm(a, f, d):
    import re
    if re.match('arm.*', a) and re.match('arm.*7.*', f):
        return '7'
    return ''

def golang_map_os(o, d):
    if o.startswith('linux'):
        return 'linux'
    return o

def golang_arch_supports_shlibs(goarch, d):
    if goarch in d.getVar('GO_SHLIB_ARCHS', True).split():
        return '1'
    return '0'
