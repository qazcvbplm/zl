package com.dingdian.order.api

import okhttp3.Interceptor
import okhttp3.Response

class CommonQueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val processedRequest = CommonQueryGenerator.generate(chain.request())
        return chain.proceed(processedRequest)
    }
}