package com.dingdian.order.ui.main.order.complete

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.dingdian.order.R
import com.dingdian.order.comm.Constants
import com.dingdian.order.databinding.CompleteFragmentBinding
import com.dingdian.order.ui.base.MVVMFragment
import com.dingdian.order.ui.widget.CustomLoadMoreView
import com.dingdian.order.ui.widget.GoodsDialog
import kotlinx.android.synthetic.main.complete_fragment.*

class CompleteFragment : MVVMFragment<CompleteViewModel, CompleteFragmentBinding>() {

    companion object {
        fun newInstance() = CompleteFragment()
    }

    val adapter by lazy { CompleteOrderAdapter() }

    override fun createViewModel(): CompleteViewModel = getViewModel(CompleteViewModel::class.java)

    override fun getLayout(): Int = R.layout.complete_fragment

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
    }

    override fun initData() {

    }


}
