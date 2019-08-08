package com.dingdian.order.api.response

open class Resp<T> {

    var code: Boolean? = null
    var msg: String? = null
    //    var code: String? = null
    var params: T? = null

    fun isSuccess(): Boolean {
        return code != null && code!!
    }

    fun isError(): Boolean {
        return !isSuccess()
    }
}