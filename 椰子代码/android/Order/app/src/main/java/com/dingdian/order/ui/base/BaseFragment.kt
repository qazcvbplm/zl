package com.dingdian.order.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dingdian.order.R
import com.dingdian.order.ui.App
import com.gyf.barlibrary.ImmersionBar
import com.kaopiz.kprogresshud.KProgressHUD
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    protected var mActivity: Activity? = null

    private var mLoading: KProgressHUD? = null

    protected var mImmersionBar: ImmersionBar? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (enableImmersionBar()) {
            mImmersionBar = ImmersionBar.with(this)
            initImmersionBar(mImmersionBar)?.init()
        }

        setHasOptionsMenu(true)

        return TextView(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    protected open fun enableImmersionBar() = false

    protected open fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return mImmersionBar
    }

    override fun onDestroy() {
        super.onDestroy()
        App.watcher!!.watch(this)
        mImmersionBar?.destroy()
    }

    protected fun dismissDialog() {
        if (mLoading != null && mLoading!!.isShowing()) {
            mLoading?.dismiss()
        }
    }

    protected fun showWaitDialog() {
        if (mLoading == null) {
            mLoading = getProgressHUD(context!!)
        }
        mLoading?.apply {
            if (!isShowing)
                mLoading?.show()
        }
    }

    private fun getProgressHUD(context: Context): KProgressHUD {
        return KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(context.getString(R.string.loading))
                .setCancellable(true)
    }


}
