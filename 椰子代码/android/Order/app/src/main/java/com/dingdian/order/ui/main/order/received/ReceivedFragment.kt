package com.dingdian.order.ui.main.order.received

import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.R
import com.dingdian.order.comm.Constants
import com.dingdian.order.databinding.ReceivedFragmentBinding
import com.dingdian.order.ui.base.MVVMFragment
import com.dingdian.order.ui.bluetooth.BtService
import com.dingdian.order.ui.widget.CustomLoadMoreView
import com.dingdian.order.ui.widget.GoodsDialog
import com.dingdian.order.ui.widget.print.PrintUtil
import com.dingdian.order.utils.SharePreUtil
import kotlinx.android.synthetic.main.received_fragment.*

class ReceivedFragment : MVVMFragment<ReceivedViewModel, ReceivedFragmentBinding>() {

    companion object {
        fun newInstance() = ReceivedFragment()
    }

    val adapter by lazy { ReceivedOrderAdapter() }

    override fun createViewModel(): ReceivedViewModel = getViewModel(ReceivedViewModel::class.java)

    override fun getLayout(): Int = R.layout.received_fragment

    override fun initView() {
        super.initView()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.setLoadMoreView(CustomLoadMoreView())
        adapter.setPreLoadNumber(Constants.PAGE_SIZE)
        adapter.setOnLoadMoreListener({
            mViewModel?.loadMore(adapter)
        }, recyclerView)

        refreshLayout.setColorSchemeColors(Color.parseColor("#FFFD4D4D"))
        refreshLayout.setOnRefreshListener {
            mViewModel?.refresh(adapter)
        }

        mViewModel?.refreshEvent?.observe(this, Observer {
            it?.let {
                refreshLayout.isRefreshing = it
            }
        })

        refreshLayout.postDelayed({
            mViewModel?.refresh(adapter)
        }, 300)

        adapter.viewGoods = {
            GoodsDialog(it.op, context!!).show()
        }

        adapter.cancel = {
            mViewModel?.cancel(it.id);
        }

        adapter.print = {
            if (!SharePreUtil.getInstance("gprs").getBoolean("gprs", false)) {
                if (Constants.isBondBluetooth) {
                    val intent = Intent(context?.applicationContext, BtService::class.java)
                    intent.action = PrintUtil.ACTION_PRINT_TICKET
                    intent.putExtra("order", it)
                    context!!.startService(intent)
                } else {
                    ToastUtils.showShort("蓝牙未连接")
                }
            } else {
                ToastUtils.showLong("gprs打印中");
            }
        }
    }

    override fun initData() {

    }

}
