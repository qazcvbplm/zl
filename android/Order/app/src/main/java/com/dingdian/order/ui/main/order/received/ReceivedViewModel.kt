package com.dingdian.order.ui.main.order.received

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.OrderList
import com.dingdian.order.bean.UserTemp
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.db.entity.User
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import com.dingdian.order.ui.login.LoginActivity
import com.dingdian.order.utils.SharePreUtil
import com.google.gson.Gson

class ReceivedViewModel @javax.inject.Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    var page = 1

    val size = Constants.PAGE_SIZE

    val refreshEvent = SingleLiveEvent<Boolean>()

    var shopId = -1

    fun cancel(id: String) {
        dataRepository.cancel(id, object : NetworkCallback<UserTemp>() {
            override fun onSuccess(response: Resp<UserTemp>) {
                super.onSuccess(response)
                response?.params?.apply {
                    val user = Gson().fromJson(msg, UserTemp::class.java)
                    ToastUtils.showShort(user.msg)
                }
            }
        })
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LocalUser.getUser().observe(owner, Observer {
            it?.apply {
                shopId = id
            }
        })
    }

    fun refresh(adapter: ReceivedOrderAdapter) {
        receivedList(adapter, true)
    }

    fun loadMore(adapter: ReceivedOrderAdapter) {
        receivedList(adapter, false)
    }

    private fun receivedList(adapter: ReceivedOrderAdapter, refresh: Boolean) {
        if (shopId < 0) return
        if (refresh) page = 1
        dataRepository.receivedList(
                shopId,
                page,
                Constants.PAGE_SIZE,
                object : NetworkCallback<OrderList>() {
                    override fun onStart() {
                        if (refresh)
                            refreshEvent.value = true
                    }

                    override fun onFinished() {
                        super.onFinished()
                        refreshEvent.value = false
                    }

                    override fun onError(throwable: Throwable?) {
                        super.onError(throwable)
                        refreshEvent.value = false
                    }

                    override fun onSuccess(response: Resp<OrderList>) {
                        super.onSuccess(response)
                        val list = response?.params?.orderList
                        if (refresh) {
                            if (list.isNullOrEmpty()) {//空数据
                                adapter.setNewData(list)
                                adapter.notifyDataSetChanged()
                                adapter.loadMoreEnd()
                                adapter.disableLoadMoreIfNotFullPage()
                            } else {
                                adapter.setNewData(list)
                                if (list.size < Constants.PAGE_SIZE) {
                                    adapter.loadMoreEnd(true)
                                    adapter.disableLoadMoreIfNotFullPage()
                                } else {
                                    page++
                                }
                            }
                        } else {
                            if (list.isNullOrEmpty()) {//没有更多数据
                                adapter.loadMoreEnd(true)
                                adapter.disableLoadMoreIfNotFullPage()
                            } else {
                                adapter.addData(list)
                                if (list.size < Constants.PAGE_SIZE) {
                                    adapter.loadMoreEnd(true)
                                    adapter.disableLoadMoreIfNotFullPage()
                                } else {
                                    page++
                                    adapter.loadMoreComplete()
                                }
                            }
                        }
                    }
                })
    }
}
