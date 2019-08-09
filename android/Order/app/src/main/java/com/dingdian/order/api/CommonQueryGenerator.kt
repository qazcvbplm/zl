package com.dingdian.order.api

import com.blankj.utilcode.util.StringUtils
import com.dingdian.order.comm.LocalUser
import okhttp3.HttpUrl
import okhttp3.Request
import java.net.URLEncoder

object CommonQueryGenerator {

    val HEADER_GENERIC_PARAMS = "Generic-Params-enabled"

    val ACCESS_TOKEN_KEY = "token"
    fun generate(request: Request): Request {
        val requestBuilder = request.newBuilder()

        if (request.header(HEADER_GENERIC_PARAMS) != null) {
            val httpUrl = request.url()
            val urlBuilder = httpUrl.newBuilder()
            try {
                LocalUser.getUser().value?.token?.let {
                    addHeader(requestBuilder, ACCESS_TOKEN_KEY, it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            requestBuilder.url(urlBuilder.build())

        }
        return requestBuilder.build()
    }

    /**
     * 添加Query参数
     *
     * @param builder
     * @param name
     * @param value
     */
    private fun addQuery(builder: HttpUrl.Builder, name: String, value: String) {
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
            builder.addQueryParameter(name, value)
        }
    }

    /**
     * 添加Header
     *
     * @param builder
     * @param name
     * @param value
     */
    private fun addHeader(builder: Request.Builder, name: String, value: String?) {
        try {
            if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
                builder.removeHeader(name).addHeader(name, URLEncoder.encode(value, "utf-8"))
            }
        } catch (e: Exception) {
        }

    }
}