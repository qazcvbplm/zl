package com.dingdian.order.ui.main.order

import android.app.Application
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel


class OrderViewModel @javax.inject.Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {
}
