package com.dingdian.order.api

import com.blankj.utilcode.util.LogUtils
import com.dingdian.order.event.TokenInvalidEvent
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject
import java.io.IOException

class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        LogUtils.d(String.format("%1\$s->%2\$s", request.method(), request.url()))
        if (request.headers() != null) {
            LogUtils.i("Headers:" + request.headers())
        }
        if (request.body() != null) {
            LogUtils.json(bodyToString(request.body()))
        }

        val response = chain.proceed(chain.request())

        val headers = response.headers()
        val sb = StringBuilder()
        for (i in 0 until headers.size()) {
//            logger.log(headers.name(i) + ": " + headers.value(i))
            sb.append(headers.name(i) + ": " + headers.value(i) + "\n")
        }
        LogUtils.json(sb.toString())
        LogUtils.json(response.code().toString())

        val mediaType = response.body()?.contentType()
        val responseBody = response.body()?.string()
        //token失效 、被踢
        responseBody?.let {
            try {
                val jsonObject = JSONObject(responseBody)
                val code = jsonObject.optString("msg")
                code?.let {
                    if (code == "1100") {
                        EventBus.getDefault().post(TokenInvalidEvent())
                    }
                }
            } catch (e: Exception) {

            }

        }

        LogUtils.json(responseBody)

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, responseBody))
                .build()
    }

    private fun bodyToString(request: RequestBody?): String? {
        if (request != null) {
            try {
                val buffer = Buffer()
                request.writeTo(buffer)
                return buffer.readUtf8()
            } catch (e: IOException) {
                LogUtils.e(e, "Did not work.")
            }

        }
        return null
    }
}