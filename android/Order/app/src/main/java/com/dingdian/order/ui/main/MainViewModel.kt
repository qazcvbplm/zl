package com.dingdian.order.ui.main

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.VersionBean
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    val updateEvent = SingleLiveEvent<VersionBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        checkVersion()
    }

    fun checkVersion() {

        dataRepository?.checkVersion(object : NetworkCallback<VersionBean>() {

            override fun onStart() {
            }

            override fun onFailed(response: Resp<VersionBean>) {}

            override fun onError(throwable: Throwable?) {
            }

            override fun onSuccess(response: Resp<VersionBean>) {
                super.onSuccess(response)
                val versionBean = response.params
                versionBean?.let {
                    if (versionBean!!.checkUpdate()) {
                        updateEvent.value = it
                    }
                }

            }
        })
    }

}