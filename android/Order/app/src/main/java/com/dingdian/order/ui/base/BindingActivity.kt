package com.dingdian.order.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding

abstract class BindingActivity<VDB : ViewDataBinding> : BaseActivity() {

    protected var mBinding: VDB? = null

    override fun initView() {
        mBinding = DataBindingUtil.setContentView(this, getLayout())
        mBinding?.setLifecycleOwner(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

}