package com.dingdian.order.ui.main.mine

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.view.Window
import com.alibaba.android.arouter.launcher.ARouter
import com.dingdian.order.api.response.Resp
import com.dingdian.order.api.response.ShopStatisticsResponse
import com.dingdian.order.bean.ShopBean
import com.dingdian.order.bean.ShopList
import com.dingdian.order.bean.ShopStatisticsBean
import com.dingdian.order.bean.ShopStatisticsResult
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import com.dingdian.order.utils.DeviceUtil
import java.text.SimpleDateFormat
import java.util.*

class MineViewModel @javax.inject.Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    val shop = ObservableField<ShopBean>()

    val version = ObservableField<String>()

    val shopStatisticsResult = ObservableField<ShopStatisticsResult>(ShopStatisticsResult())

    val isLogin = ObservableBoolean(false)

    var startDate = ""
    var endDate = ""

    val startTime = " 00:00:01"
    val endTime = " 23:59:59"

    init {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        startDate = sdf.format(Calendar.getInstance().time) + startTime
        endDate = sdf.format(Calendar.getInstance().time) + endTime
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        version.set(DeviceUtil.getCurrentVersion())
        LocalUser.getUser().observe(owner, Observer {
            isLogin.set(LocalUser.isLogin())
        })
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (LocalUser.isLogin()) {
            shopInfo()
            shopStatistics()
        }

    }

    fun shopInfo() {
        dataRepository.shopInfo(LocalUser.getUser().value!!.id.toString(), object : NetworkCallback<ShopList>() {
            override fun onSuccess(response: Resp<ShopList>) {
                super.onSuccess(response)
                response?.params?.list?.apply {
                    if (size > 0) {
                        shop.set(this[0])
                    }
                }
            }
        })
    }

    fun shopStatistics() {
        dataRepository.shopStatistics(startDate, endDate, LocalUser.getUser().value!!.id.toString(), object : NetworkCallback<ShopStatisticsBean>() {
            override fun onSuccess(response: Resp<ShopStatisticsBean>) {
                super.onSuccess(response)
                response?.params?.result?.apply {
                    shopStatisticsResult.set(this)
                }
            }
        })
    }

    fun query(view: View) {
        if (LocalUser.isLogin()) {
            shopStatistics()
        }
    }

    fun comment(view: View) {
        ARouter.getInstance().build(ArouterPath.comment).navigation()
    }

    fun goods(view: View) {
        ARouter.getInstance().build(ArouterPath.goods).navigation()
    }

    fun setting(view: View) {
        ARouter.getInstance().build(ArouterPath.shop).navigation()
    }

    fun logout(view: View) {
        if (LocalUser.isLogin()) {
            LocalUser.logout()
            ARouter.getInstance()
                    .build(ArouterPath.login)
                    .navigation()
        }
    }
}
