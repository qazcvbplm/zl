package com.dingdian.order.utils

import android.content.Context
import com.blankj.utilcode.util.SPUtils

object SharePreUtil {
    const val commmon_group = "common_group"
    const val local_key = "local"
    const val accout_key = "accout_key"
    const val password_key = "password_key"
    const val last_version_key = "last_version_key"
    const val auto_receive_key = "auto_receive_key"
    const val small_font_key = "small_font_key"

    const val device_group = "device_preference"
    const val uuid_key = "uuid"

    fun getInstance(group: String) = SPUtils.getInstance(group, Context.MODE_PRIVATE)
}