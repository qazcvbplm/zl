package com.dingdian.order.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BindingFragment<VDB : ViewDataBinding> : BaseFragment() {

    protected var mBinding: VDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        return mBinding?.root
    }

    override fun onDestroy() {
        mBinding?.unbind()
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun getLayout(): Int

    override fun initView() {
        mBinding?.setLifecycleOwner(this)
    }
}