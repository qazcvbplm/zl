package com.dingdian.order.ui.goods

import android.app.Application
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.GoodsAttr
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

class EditViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    val addSuccessEvent = SingleLiveEvent<Int>()

    val updateSuccessEvent = SingleLiveEvent<Int>()

    fun removeAttr(id: Int, adapter: AttrListAdapter, position: Int) {
        dataRepository.removeAttr(id, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                adapter.list.removeAt(position)
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun addAttr(attr: GoodsAttr, pid: Int, adapter: AttrListAdapter) {
        dataRepository.addAttr(attr.name, attr.price.toString(), pid, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                adapter.list.add(0, attr)
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun updateGoods(map: HashMap<String, Any>) {
        dataRepository?.updateGoods(map, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                addSuccessEvent.value = 1
            }
        })
    }

    fun addGoods(map: HashMap<String, Any>) {
        dataRepository?.addGoods(map, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                addSuccessEvent.value = 1
            }
        })
    }

}