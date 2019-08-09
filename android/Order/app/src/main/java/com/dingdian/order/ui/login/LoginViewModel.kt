package com.dingdian.order.ui.login

import android.app.Application
import android.content.Context
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.UserTemp
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.db.entity.User
import com.dingdian.order.db.entity.UserT
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import com.dingdian.order.utils.SharePreUtil
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {


    fun login(name: String, password: String, context: Context) {
        dataRepository.login(name, password, object : NetworkCallback<UserTemp>() {
            override fun onSuccess(response: Resp<UserTemp>) {
                super.onSuccess(response)
                response?.params?.apply {
                    val user = Gson().fromJson(msg, User::class.java)
                    val json = Gson().fromJson(msg, UserT::class.java)
                    user.token = token
                    user.lat = json.txAmount.toString()
                    LocalUser.login(user)
                    SharePreUtil.getInstance(SharePreUtil.commmon_group).put(SharePreUtil.accout_key, name)
                    SharePreUtil.getInstance(SharePreUtil.commmon_group).put(SharePreUtil.password_key, password)
                    SharePreUtil.getInstance("shop").put("shop_txAmount", user.lat)
                    ARouter.getInstance().build(ArouterPath.main).navigation()
                    context as LoginActivity
                    context.finish()
                }
            }
        })

    }
}