package com.dingdian.order.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.dingdian.order.event.NetWorkMessage
import com.dingdian.order.showShort
import com.dingdian.order.ui.base.BindingFragment
import com.dingdian.order.ui.base.DataViewModel
import javax.inject.Inject

abstract class MVVMFragment<VM : DataViewModel, VDB : ViewDataBinding> : BindingFragment<VDB>() {
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
                    context?.showShort(getString(it.id))
                else {
                    it.string?.let { str ->
                        context?.showShort(str)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        mViewModel?.apply {
            lifecycle.addObserver(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.apply {
            lifecycle.removeObserver(this)
        }
    }

    override fun initView() {
        mViewModel?.let {
            it.loadingUI.addOnPropertyChangedCallback(loadingCallback)
        }
        mViewModel?.let {
            it.errText.addOnPropertyChangedCallback(errCallback)
        }
    }

    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
        return ViewModelProviders.of(this, mViewModelFactory).get(modelClass)
    }

    fun <T : ViewModel> getViewModel(modelClass: Class<T>, factory: ViewModelProvider.Factory?): T {
        return ViewModelProviders.of(this, factory).get(modelClass)
    }

    fun getViewModelFactory(): ViewModelProvider.Factory? {
        return mViewModelFactory
    }

    abstract fun createViewModel(): VM

    override fun onDetach() {
        super.onDetach()
        mViewModel?.let {
            it.loadingUI.removeOnPropertyChangedCallback(loadingCallback)
        }

        mViewModel?.let {
            it.errText.removeOnPropertyChangedCallback(errCallback)
        }
    }
}