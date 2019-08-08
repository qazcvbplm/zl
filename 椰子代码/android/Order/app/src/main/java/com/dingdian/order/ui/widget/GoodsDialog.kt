package com.dingdian.order.ui.widget

import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dingdian.order.R
import com.dingdian.order.bean.Op
import com.dingdian.order.databinding.ItemOpListBinding
import kotlinx.android.synthetic.main.dialog_goods_list.*

class GoodsDialog constructor(val list: List<Op>, context: Context) : Dialog(context) {

    val adapter by lazy { GoodsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_goods_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.setNewData(list)

        closeBtn.setOnClickListener { dismiss() }
    }

    class GoodsListAdapter : BaseQuickAdapter<Op, GoodsListAdapter.ViewHolder>(R.layout.item_op_list) {
        override fun convert(helper: ViewHolder, item: Op) {
            helper.binding.model = item
        }

        override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                    ?: return super.getItemView(layoutResId, parent)
            val view = binding.root
            view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
            return view
        }

        class ViewHolder(view: View) : BaseViewHolder(view) {

            val binding: ItemOpListBinding
                get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemOpListBinding
        }
    }


}