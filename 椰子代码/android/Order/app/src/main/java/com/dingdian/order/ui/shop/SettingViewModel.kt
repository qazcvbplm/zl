package com.dingdian.order.ui.shop

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.dingdian.order.api.response.Resp
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

class SettingViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    val updateEvent = SingleLiveEvent<HashMap<String, Any>>()

    fun shopUpdate(map: HashMap<String, Any>) {
        dataRepository.shopUpdate(map, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                updateEvent.value = map
            }
        })
    }
}