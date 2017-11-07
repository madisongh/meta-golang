inherit go

GOLANG_WARN_CONVERSIONS ??= "1"

addhandler golang_conversion_handler
golang_conversion_handler[eventmask] = "bb.event.RecipePreFinalise"
python golang_conversion_handler() {
    import re
    if e.data.getVar("GOLANG_WARN_CONVERSIONS") != "0":
        bb.warn("recipe inherits golang.bbclass, attempting to convert")
    go_srcroot = e.data.getVar("GO_SRCROOT", False)
    if go_srcroot is None:
        return
    src_uri = e.data.getVar("SRC_URI", False)
    src_uri, n = re.subn(r"destsuffix=\$\{GO_SRCROOT\}",
			 r"destsuffix=git/src/${GO_IMPORT}", src_uri)
    replacements = n
    src_uri, n = re.subn(r"git://\${GO_SRCROOT\}", r"git://${GO_IMPORT}", src_uri)
    replacements += n
    src_uri, n = re.subn(r"git://git@\${GO_SRCROOT\}", r"git://git@${GO_IMPORT}", src_uri)
    replacements += n
    if replacements > 0:
        e.data.setVar("SRC_URI", src_uri)
    s = e.data.getVar("GO_INSTALL", False)
    s, replacements = re.subn(r"\$\{GO_SRCROOT\}", r"${GO_IMPORT}", s)
    if replacements > 0:
        e.data.setVar("GO_INSTALL", s)
    s = e.data.getVar("GO_INSTALL_FILTEROUT", False)
    s, replacements = re.subn(r"\$\{GO_SRCROOT\}", r"${GO_IMPORT}", s)
    if replacements > 0:
        e.data.setVar("GO_INSTALL_FILTEROUT", s)
    e.data.setVar("GO_SRCROOT", "src/${GO_IMPORT}")
    e.data.setVar("GO_IMPORT", go_srcroot)
    e.data.setVar("S", "${WORKDIR}/git")
}
