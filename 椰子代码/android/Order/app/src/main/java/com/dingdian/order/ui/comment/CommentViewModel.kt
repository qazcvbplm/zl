package com.dingdian.order.ui.comment

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.CommentList
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

class CommentViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    var page = 1

    val size = Constants.PAGE_SIZE

    val refreshEvent = SingleLiveEvent<Boolean>()

    var shopId = -1

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LocalUser.getUser().observe(owner, Observer {
            it?.apply {
                shopId = id
            }
        })
    }

    fun refresh(adapter: CommentListAdapter) {
        commentList(adapter, true)
    }

    fun loadMore(adapter: CommentListAdapter) {
        commentList(adapter, false)
    }

    private fun commentList(adapter: CommentListAdapter, refresh: Boolean) {
        if (shopId < 0) return
        if (refresh) page = 1
        dataRepository.commentList(
                page,
                Constants.PAGE_SIZE,
                shopId.toString(),
                object : NetworkCallback<CommentList>() {
                    override fun onStart() {
                        refreshEvent.value = true
                    }

                    override fun onFinished() {
                        super.onFinished()
                        refreshEvent.value = false
                    }

                    override fun onSuccess(response: Resp<CommentList>) {
                        super.onSuccess(response)
                        val list = response?.params?.list
                        if (refresh) {
                            if (list.isNullOrEmpty()) {//空数据
                                adapter.setNewData(list)
                                adapter.notifyDataSetChanged()
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