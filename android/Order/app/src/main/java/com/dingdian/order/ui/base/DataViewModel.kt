package com.dingdian.order.ui.base

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.dingdian.order.R
import com.dingdian.order.api.response.Resp
import com.dingdian.order.event.NetWorkMessage
import retrofit2.Call
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.annotation.Nonnull
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

open class DataViewModel @Inject constructor(
        @Nonnull val context: Application,
        dataRepository: DataRepository
) : BaseViewModule<DataRepository>(context, dataRepository) {

    val loadingUI = ObservableBoolean()

    val errText = ObservableField<NetWorkMessage>()

    abstract inner class NetworkCallback<T> {
        fun onFailure(call: Call<out Resp<T>>?, t: Throwable?) {
            onError(t)
            onFinished()
        }

        fun onResponse(call: Call<out Resp<T>>?, response: Response<out Resp<T>>?) {
            val body = response?.body()
            body?.let {
                if (it.isSuccess()) {
                    onSuccess(it)
                } else {
                    onFailed(it)
                }
            }
            onFinished()
        }

        open fun onStart() {
            loadingUI.set(true)
        }

        open fun onFinished() {
            loadingUI.set(false)
        }

        open fun onError(throwable: Throwable?) {
//            if (!NetworkUtils.isAvailableByPing()) {
//                errText.set(context.getString(R.string.result_network_unavailable_error))
//            }
            when (throwable) {
                is ConnectException ->
                    errText.set(NetWorkMessage(NetWorkMessage.TYPE_ID, R.string.result_connect_failed_error, ""))
                is SocketTimeoutException -> errText.set(
                        NetWorkMessage(
                                NetWorkMessage.TYPE_ID,
                                R.string.result_connect_timeout_error,
                                ""
                        )
                )
                is SSLHandshakeException -> errText.set(
                        NetWorkMessage(
                                NetWorkMessage.TYPE_ID,
                                R.string.result_empty_error,
                                ""
                        )
                )
                else -> errText.set(NetWorkMessage(NetWorkMessage.TYPE_ID, R.string.result_empty_error, ""))
            }
        }

        open fun onSuccess(response: Resp<T>) {
//            errText.set(NetWorkMessage(NetWorkMessage.TYPE_STRING,0,response.msg))
        }

        open fun onFailed(response: Resp<T>) {
//            if (code == "401") {
            //TOKEN 过期
//                LocalUser.logout()
//                ARouter.getInstance().build(ArouterPath.login).navigation()
//                return
//            }
            errText.set(NetWorkMessage(NetWorkMessage.TYPE_STRING, 0, response.msg))

        }


    }

}