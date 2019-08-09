package com.dingdian.order.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.alibaba.android.arouter.launcher.ARouter
import com.dingdian.order.R
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.event.NetWorkMessage
import com.dingdian.order.event.TokenInvalidEvent
import com.dingdian.order.ui.App.Companion.watcher
import com.dingdian.order.ui.login.LoginActivity
import com.gyf.barlibrary.ImmersionBar
import com.kaopiz.kprogresshud.KProgressHUD
import com.noober.background.BackgroundLibrary
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var mLoading: KProgressHUD? = null

    protected var mImmersionBar: ImmersionBar? = null

//    @Inject
//    @JvmField
//    var user:LocalUser?=null

    @Inject
    @JvmField
    internal var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        if (enableImmersionBar()) {
            mImmersionBar = ImmersionBar.with(this)
            initImmersionBar(mImmersionBar)?.init()
        }
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
        initView()
        initData()
    }

    protected open fun enableImmersionBar() = false

    protected open fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return mImmersionBar
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
        super.onDestroy()
        watcher!!.watch(this)
        mImmersionBar?.destroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector!!
    }

    @LayoutRes
    protected abstract fun getLayout(): Int

    abstract fun initView()

    abstract fun initData()

    protected fun dismissDialog() {
        if (mLoading != null && mLoading!!.isShowing()) {
            mLoading?.dismiss()
        }
    }

    protected fun showWaitDialog() {
        if (mLoading == null) {
            mLoading = getProgressHUD(this)
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

    /**
     * 手动隐藏键盘
     */
    private fun hideSoftWindow() {
        val im = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus
        if (view != null) {
            val active = im!!.isActive
            if (active) {
                im!!.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    override fun finish() {
        hideSoftWindow()
        super.finish()
    }

    @Subscribe
    fun onNetWorkEvent(event: NetWorkMessage) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventTokenInvalid(event: TokenInvalidEvent) {
        if (LocalUser.isLogin()) {
            LocalUser.logout()
            ARouter.getInstance()
                    .build(ArouterPath.login)
                    .navigation()
        }
    }

}
