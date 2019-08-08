package com.dingdian.order.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.ViewDataBinding
import com.dingdian.order.event.NetWorkMessage
import com.dingdian.order.showShort
import com.dingdian.order.ui.base.BindingActivity
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

abstract class MVVMActivity<VM : DataViewModel, VDB : ViewDataBinding> : BindingActivity<VDB>() {
    @Inject
    @JvmField
    protected var mViewModelFactory: ViewModelProvider.Factory? = null
    protected var mViewModel: VM? = null

    private val loadingCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            mViewModel?.loadingUI?.get()?.let {
                if (it) {
                    showWaitDialog()
                } else {
                    dismissDialog()
                }
            }
        }
    }

    private val errCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            mViewModel?.errText?.get()?.let {
                if (it.type == NetWorkMessage.TYPE_ID)
                    showShort(getString(it.id))
                else {
                    it.string?.let { string ->
                        showShort(string)
                    }
                }
            }
        }
    }

    override fun initView() {
        mViewModel = createViewModel()
        mViewModel?.let {
            lifecycle.addObserver(it)
        }
        mViewModel?.let {
            it.loadingUI.addOnPropertyChangedCallback(loadingCallback)
        }
        mViewModel?.let {
            it.errText.addOnPropertyChangedCallback(errCallback)
        }
        super.initView()
    }

    fun <T : DataViewModel> getViewModel(modelClass: Class<T>): T {
        return ViewModelProviders.of(this, mViewModelFactory).get(modelClass)
    }

    fun <T : DataViewModel> getViewModel(modelClass: Class<T>, factory: ViewModelProvider.Factory?): T {
        return ViewModelProviders.of(this, factory).get(modelClass)
    }

    fun getViewModelFactory(): ViewModelProvider.Factory? {
        return mViewModelFactory
    }

    abstract fun createViewModel(): VM

    override fun onDestroy() {
        mViewModel?.let {
            it.loadingUI.removeOnPropertyChangedCallback(loadingCallback)
        }

        mViewModel?.let {
            it.errText.removeOnPropertyChangedCallback(errCallback)
        }
        mViewModel?.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}