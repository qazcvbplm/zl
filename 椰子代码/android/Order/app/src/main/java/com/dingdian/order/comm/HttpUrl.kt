package com.dingdian.order.comm

object HttpUrl {
    val baseUrl = when (Constants.environment) {
        Constants.environment_release -> "https://www.chuyinkeji.cn/ops/"
        else -> "https://www.chuyinkeji.cn/ops/"
    }

    val baseWebUrl = when (Constants.environment) {
        Constants.environment_release -> "https://www.chuyinkeji.cn/ops/"
        else -> "https://www.chuyinkeji.cn/ops/"
    }
}