package com.dingdian.order.ui.goods

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dingdian.order.R
import com.dingdian.order.bean.GoodsBean
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.databinding.ItemGoodsListBinding

class GoodsListAdapter : BaseQuickAdapter<GoodsBean, GoodsListAdapter.ViewHolder>(R.layout.item_goods_list) {

    var deleteCallback: ((GoodsBean) -> Unit)? = null

    override fun convert(helper: ViewHolder, item: GoodsBean) {
        helper.binding.model = item
        helper.binding.status.isSelected = item.show()
        helper.binding.edit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(EditActivity.GOODS_KEY, item)
            ARouter.getInstance()
                    .build(ArouterPath.edit)
                    .withBundle(EditActivity.BUNDLE_KEY, bundle)
                    .navigation()
        }

        helper.binding.originPrice.paint.isAntiAlias = true;//抗锯齿

//        helper.binding.originPrice.paint.flags = Paint. STRIKE_THRU_TEXT_FLAG; //中划线

        helper.binding.originPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG;  // 设置中划线并加清晰

        helper.binding.delete.setOnClickListener {
            deleteCallback?.invoke(item)
        }
    }

    fun getPositionByItem(item: GoodsBean): Int {
        var position = -1
        for (i in 0..data.lastIndex) {
            if (data[i].id == item.id) {
                return i
            }
        }
        return position
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {

        val binding: ItemGoodsListBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemGoodsListBinding
    }
}