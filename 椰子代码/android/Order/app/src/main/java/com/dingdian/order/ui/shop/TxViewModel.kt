package com.dingdian.order.ui.shop

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.UserTemp
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.db.entity.User
import com.dingdian.order.db.entity.UserT
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import com.dingdian.order.ui.login.LoginActivity
import com.dingdian.order.utils.SharePreUtil
import com.google.gson.Gson
import javax.inject.Inject

class TxViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    fun tx(shopId: String) {
        dataRepository.tx(shopId, object : NetworkCallback<UserTemp>() {
            override fun onSuccess(response: Resp<UserTemp>) {
                super.onSuccess(response)
                response?.params?.apply {
                    val user = Gson().fromJson(msg, UserTemp::class.java)
                    ToastUtils.showShort(user.msg)
                }
            }
        })
    }

}