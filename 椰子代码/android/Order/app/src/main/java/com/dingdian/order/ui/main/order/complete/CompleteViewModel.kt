package com.dingdian.order.ui.main.order.complete

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.CompleteList
import com.dingdian.order.bean.OrderList
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel

class CompleteViewModel @javax.inject.Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    var page = 1

    val size = Constants.PAGE_SIZE

    val refreshEvent = SingleLiveEvent<Boolean>()

    var shopId = -1

    var schoolId = -1

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LocalUser.getUser().observe(owner, Observer {
            it?.apply {
                shopId = id
                this@CompleteViewModel.schoolId = this.schoolId
            }
        })
    }

    fun refresh(adapter: CompleteOrderAdapter) {
        receivedList(adapter, true)
    }

    fun loadMore(adapter: CompleteOrderAdapter) {
        receivedList(adapter, false)
    }

    private fun receivedList(adapter: CompleteOrderAdapter, refresh: Boolean) {
        if (shopId < 0) return
        if (refresh) page = 1

        val hashMap = LinkedHashMap<String, Any>()
        hashMap["shopId"] = shopId
        hashMap["page"] = page
        hashMap["size"] = Constants.PAGE_SIZE
        hashMap["status"] = "已完成"
        hashMap["schoolId"] = schoolId
        dataRepository.completeList(
                hashMap,
                object : NetworkCallback<CompleteList>() {
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

                    override fun onSuccess(response: Resp<CompleteList>) {
                        super.onSuccess(response)
                        val list = response?.params?.list
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
