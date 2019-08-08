package com.dingdian.order.ui.goods

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.dingdian.order.R
import com.dingdian.order.bean.GoodsBean
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.databinding.ActivityGoodsBinding
import com.dingdian.order.ui.base.MVVMActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_goods.*

@Route(path = ArouterPath.goods)
class GoodsActivity : MVVMActivity<GoodsViewModel, ActivityGoodsBinding>() {

    val adapter by lazy { GoodsListAdapter() }

    override fun createViewModel(): GoodsViewModel = getViewModel(GoodsViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_goods

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun initView() {
        super.initView()
        mBinding?.viewModel = mViewModel
        toolbar.setNavigationOnClickListener { finish() }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        mViewModel?.goodsList?.observe(this, Observer {
            adapter.setNewData(it)
        })

        adapter.deleteCallback = {
            AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定要删除该商品吗？")
                    .setPositiveButton("确定") { _, _ ->
                        mViewModel?.delGoods(adapter, it)
                    }
                    .setNegativeButton("取消") { dialog, _ ->
                        dialog.dismiss()
                    }.create().show()
        }


    }

    override fun initData() {

    }

}
