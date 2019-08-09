package com.dingdian.order.ui.shop

import com.alibaba.android.arouter.facade.annotation.Route
import com.dingdian.order.R
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.databinding.ActivityTxBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.dingdian.order.utils.SharePreUtil
import kotlinx.android.synthetic.main.activity_tx.*
import kotlinx.android.synthetic.main.activity_tx.tx

@Route(path = ArouterPath.tx)
class TxActivity : MVVMActivity<TxViewModel, ActivityTxBinding>() {
    override fun initData() {
        money.setText("余额：" + SharePreUtil.getInstance("shop").getString("shop_txAmount", "0.00") + " 元");

        tx.setOnClickListener {
            mViewModel?.tx(LocalUser.getUser().value?.id.toString())
        }
    }

    override fun getLayout(): Int = R.layout.activity_tx

    override fun createViewModel(): TxViewModel = getViewModel(TxViewModel::class.java)


}
