package com.dingdian.order.event

data class NetWorkMessage(var type: Int, var id: Int, var string: String?) {
    companion object {
        const val TYPE_ID = 0
        const val TYPE_STRING = 1
    }
}