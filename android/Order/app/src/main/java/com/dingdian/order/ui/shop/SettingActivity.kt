package com.dingdian.order.ui.shop

import android.arch.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.R
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.databinding.ActivitySettingBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_setting.*

@Route(path = ArouterPath.shop)
class SettingActivity : MVVMActivity<SettingViewModel, ActivitySettingBinding>() {

    override fun createViewModel(): SettingViewModel = getViewModel(SettingViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_setting

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationOnClickListener { finish() }

        LocalUser.getUser().observe(this, Observer {
            it?.let { user ->
                shopName.setText(user.shopName)
                shopName.setSelection(user.shopName.length)
                topTitle.setText(user.topTitle)
                topTitle.setSelection(user.topTitle.length)
                shopPhone.setText(user.shopPhone)
                shopPhone.setSelection(user.shopPhone.length)
                startPrice.setText(user.startPrice)
                startPrice.setSelection(user.startPrice.length)
                sendModeFlag.isChecked = user.sendModelFlag == 1
                getModeFlag.isChecked = user.getModelFlag == 1
                tsModelFlag.isChecked = user.tsModelFlag == 1
                openFlag.isChecked = user.openFlag == 1
            }
        })

        updateBtn.setOnClickListener {
            update()
        }

        mViewModel?.updateEvent?.observe(this, Observer { map ->
            ToastUtils.showShort("更新成功")

            val user = LocalUser.getUser().value
            user?.let {
                it.shopName = map!!["shopName"] as String
                it.topTitle = map!!["topTitle"] as String
                it.shopPhone = map!!["shopPhone"] as String
                it.startPrice = map!!["startPrice"] as String
                it.sendModelFlag = map!!["sendModeFlag"] as Int
                it.getModelFlag = map!!["getModeFlag"] as Int
                it.tsModelFlag = map!!["tsModelFlag"] as Int
                it.openFlag = map!!["openFlag"] as Int
                LocalUser.updateUser(it)
            }

            finish()
        })
    }

    override fun initData() {

    }

    private fun update() {

        LocalUser.getUser().value?.let {
            val shopName = shopName.text.toString()
            val title = topTitle.text.toString()
            val phone = shopPhone.text.toString()
            val startPrice = startPrice.text.toString()
            val sendFlag = if (sendModeFlag.isChecked) 1 else 0
            val getFlag = if (getModeFlag.isChecked) 1 else 0
            val tsFlag = if (tsModelFlag.isChecked) 1 else 0
            val isOpen = if (openFlag.isChecked) 1 else 0

            val hashMap = LinkedHashMap<String, Any>()
            hashMap["topTitle"] = title
            hashMap["shopName"] = shopName
            hashMap["shopPhone"] = phone
            hashMap["startPrice"] = startPrice
            hashMap["sendModeFlag"] = sendFlag
            hashMap["getModeFlag"] = getFlag
            hashMap["tsModelFlag"] = tsFlag
            hashMap["openFlag"] = isOpen
            hashMap["id"] = it.id

            mViewModel?.shopUpdate(hashMap)
        }
    }


}
