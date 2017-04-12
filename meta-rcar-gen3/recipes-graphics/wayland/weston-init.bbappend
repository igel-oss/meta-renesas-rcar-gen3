require include/gles-control.inc
require include/multimedia-control.inc

WESTONARGS ??= "--idle-time=0"

do_install_append() {
    sed -e 's,launcher="weston-launch.*--",launcher="weston-launch -u root --",g' \
        -e 's,exec openvt $openvt_args --,exec ,g' \
        -i ${D}${bindir}/weston-start

    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "s/\$OPTARGS/${WESTONARGS} \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston.service
    fi

    if [ "X${USE_MULTIMEDIA}" = "X1" ]; then
        if [ "X${USE_V4L2_RENDERER}" = "X1" ]; then
            sed -e "s/\$OPTARGS/--use-v4l2 \$OPTARGS/" \
                -i ${D}/${systemd_system_unitdir}/weston.service
        fi
    fi
}
