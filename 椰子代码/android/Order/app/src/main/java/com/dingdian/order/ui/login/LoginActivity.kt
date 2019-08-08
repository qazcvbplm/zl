package com.dingdian.order.ui.login

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dingdian.order.R
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.databinding.ActivityLoginBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.dingdian.order.utils.SharePreUtil
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = ArouterPath.login)
class LoginActivity : MVVMActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun createViewModel(): LoginViewModel = getViewModel(LoginViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_login

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!this.isTaskRoot) {
            val mainIntent = intent
            val action = mainIntent.action
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action == Intent.ACTION_MAIN) {
                finish()
                return
            }
        }
    }

    override fun initView() {
        super.initView()
        mBinding?.viewModel = mViewModel

        val name = SharePreUtil.getInstance(SharePreUtil.commmon_group).getString(SharePreUtil.accout_key, "")
        val pwd = SharePreUtil.getInstance(SharePreUtil.commmon_group).getString(SharePreUtil.password_key, "")

        account.setText(name)
        password.setText(pwd)

    }

    override fun initData() {

    }

}
