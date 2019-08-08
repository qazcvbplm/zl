package com.dingdian.order.ui.goods

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.graphics.Color
import android.view.View
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.picker.SinglePicker
import com.alibaba.android.arouter.launcher.ARouter
import com.dingdian.order.api.response.Resp
import com.dingdian.order.bean.GoodTypeBean
import com.dingdian.order.bean.GoodTypeList
import com.dingdian.order.bean.GoodsBean
import com.dingdian.order.bean.GoodsList
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.event.SingleLiveEvent
import com.dingdian.order.ui.base.BaseActivity
import com.dingdian.order.ui.base.DataRepository
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

class GoodsViewModel @Inject constructor(app: Application, val dataRepository: DataRepository) :
        DataViewModel(app, dataRepository) {

    var shopId = ""

    var typeId = 0

    var typeName = ObservableField<String>("分类")

    var goodsTypeList = arrayListOf<GoodTypeBean>()

    val goodsList = SingleLiveEvent<ArrayList<GoodsBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LocalUser.getUser().observe(owner, Observer {
            it?.let { user ->
                shopId = user.id.toString()
                goodTypeList()
            }
        })
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (goodsTypeList.size > 0) {
            goodsList()
        }
    }

    fun goodTypeList() {
        dataRepository.goodTypeList(shopId, object : NetworkCallback<GoodTypeList>() {
            override fun onSuccess(response: Resp<GoodTypeList>) {
                super.onSuccess(response)
                response?.params?.let {
                    goodsTypeList.addAll(it.list)
                    typeId = goodsTypeList[0].id
                    typeName.set(goodsTypeList[0].name)
                    goodsList()
                }
            }
        })
    }

    fun goodsList() {
        dataRepository.goodsList(typeId, object : NetworkCallback<GoodsList>() {
            override fun onSuccess(response: Resp<GoodsList>) {
                super.onSuccess(response)
                response?.params?.list?.let {
                    goodsList.value = it
                }
            }
        })
    }

    fun delGoods(adapter: GoodsListAdapter, item: GoodsBean) {
        dataRepository.delGoods(item.id, 1, object : NetworkCallback<String>() {
            override fun onSuccess(response: Resp<String>) {
                super.onSuccess(response)
                val position = adapter.getPositionByItem(item)
                if (position >= 0) {
                    adapter.remove(position)
                }


            }
        })
    }

    fun onConstellationPicker(view: View) {

        if (goodsTypeList == null || goodsTypeList.size < 1) return

        val stringArray = goodsTypeList.map {
            it.name
        }
        val picker = SinglePicker(view.context as BaseActivity, stringArray)
        picker.window.decorView.setOnTouchListener { v, event ->
            if (picker.isShowing) {
                picker.dismiss()
                return@setOnTouchListener true
            }
            false
        }
        picker.setCanLoop(false)//不禁用循环
        picker.setTopBackgroundColor(Color.parseColor("#F3F2Fc"))
        picker.setTopHeight(50)
        picker.setTopLineColor(-0xcc4a1b)
        picker.setTopLineHeight(0)

        picker.setTitleText("类别")
        picker.setTitleTextColor(Color.parseColor("#000000"))
        picker.setTitleTextSize(16)

        picker.setCancelTextColor(Color.parseColor("#518de6"))
        picker.setCancelTextSize(14)

        picker.setSubmitTextColor(Color.parseColor("#518de6"))
        picker.setSubmitTextSize(14)

        picker.setSelectedTextColor(Color.parseColor("#000000"))
        picker.setUnSelectedTextColor(Color.parseColor("#696969"))
        picker.setWheelModeEnable(false)
        val config = LineConfig()
        config.color = Color.parseColor("#696969")//线颜色
        config.alpha = 80//线透明度
        //        config.setRatio(1);//线比率
        picker.setLineConfig(config)
        picker.setWheelModeEnable(true)
        picker.setItemWidth(picker.screenWidthPixels)
        picker.setBackgroundColor(Color.parseColor("#F3F2Fc"))
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.selectedIndex = 0
        picker.setOnItemPickListener { index, item ->
            goodsTypeList.forEach {
                if (it.name == item) {
                    typeId = it.id
                    goodsList()
                }
            }

        }
        picker.show()
    }

    fun addGoods(view: View) {
        ARouter.getInstance()
                .build(ArouterPath.edit)
                .withString(EditActivity.CATEGORYID_KEY, typeId.toString())
                .navigation()
    }

}