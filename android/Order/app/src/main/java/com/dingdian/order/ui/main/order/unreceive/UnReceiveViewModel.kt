package com.dingdian.order.ui.main.order.unreceive

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.MakeOrder
import com.dingdian.order.bean.OrderBean
import com.dingdian.order.bean.OrderList
import com.dingdian.order.bean.UserTemp
import com.dingdian.order.comm.Constants
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.db.entity.User
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import com.dingdian.order.ui.bluetooth.BtService
import com.dingdian.order.ui.widget.print.PrintUtil

class UnReceiveViewModel @javax.inject.Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    val orderList = SingleLiveEvent<ArrayList<OrderBean>>()

    val refreshEvent = SingleLiveEvent<Boolean>()

    val receiveSuccess = SingleLiveEvent<Boolean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        if (LocalUser.isLogin()) {
            unReceiveList()
        }
    }


    fun unReceiveList() {
        dataRepository.unReceiveList(LocalUser.getUser()!!.value!!.id, object : NetworkCallback<OrderList>() {

            override fun onStart() {
//                super.onStart()
//                refreshEvent.value = true
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
                response.params?.orderList?.let {
                    if (it.size > 0)
                        orderList.value = response?.params?.orderList
                }

            }
        })
    }

    fun receiveOrder(adapter: UnReceiveOrderAdapter, item: OrderBean) {
        dataRepository.receiveOrder(item.id, object : NetworkCallback<MakeOrder>() {
            override fun onSuccess(response: Resp<MakeOrder>) {
                super.onSuccess(response)
                if (Constants.isBondBluetooth) {
                    val intent = Intent(context?.applicationContext, BtService::class.java)
                    intent.action = PrintUtil.ACTION_PRINT_TICKET
                    intent.putExtra("order", response.params!!.bean)
                    context!!.startService(intent)
                }
                for (i in 0..adapter.data.lastIndex) {
                    if (adapter.data[i].id == item.id) {
                        adapter.remove(i)
                        adapter.notifyItemRemoved(i)
                        receiveSuccess.value = true
                        return
                    }
                }
            }
        })
    }

}
