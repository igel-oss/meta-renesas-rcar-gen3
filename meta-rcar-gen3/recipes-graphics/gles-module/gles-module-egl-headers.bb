require include/gles-control.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/gles-user-module:"

DESCRIPTION = "PowerVR GPU user EGL header files"
LICENSE = "CLOSED"

PN = "gles-module-egl-headers"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/rogue"

SRC_URI_r8a7795 = "file://r8a7795_linux_gsx_binaries_gles3.tar.bz2"
SRC_URI_r8a7796 = "file://r8a7796_linux_gsx_binaries_gles3.tar.bz2"
SRC_URI_append = " \
    file://0001-EGL-eglext.h-Include-eglmesaext.h-to-avoid-compile-error.patch \
"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

# The headers need headers from virtual/mesa.
do_populate_sysroot[depends] += "virtual/mesa:do_populate_sysroot"

do_install() {
    # Install header files
    install -d ${D}/${includedir}/EGL
    install -m 644 ${S}/${includedir}/EGL/*.h ${D}/${includedir}/EGL/
    install -d ${D}/${includedir}/KHR
    install -m 644 ${S}/${includedir}/KHR/khrplatform.h ${D}/${includedir}/KHR/khrplatform.h
}

RDEPENDS_${PN}-dev = ""
