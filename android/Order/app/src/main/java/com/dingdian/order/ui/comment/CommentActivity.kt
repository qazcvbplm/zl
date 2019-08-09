package com.dingdian.order.ui.comment

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.dingdian.order.R
import com.dingdian.order.bean.CommentBean
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.Constants
import com.dingdian.order.databinding.ActivityCommentBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.dingdian.order.ui.widget.CustomLoadMoreView
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_comment.*

@Route(path = ArouterPath.comment)
class CommentActivity : MVVMActivity<CommentViewModel, ActivityCommentBinding>() {

    val adapter by lazy { CommentListAdapter() }

    override fun createViewModel(): CommentViewModel = getViewModel(CommentViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_comment

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun initView() {
        super.initView()
        toolbar.setNavigationOnClickListener { finish() }

        recyclerView.layoutManager = LinearLayoutManager(this)
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


    }

    override fun initData() {

    }
}
