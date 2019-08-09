package com.dingdian.order.ui.main.order.complete

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dingdian.order.R
import com.dingdian.order.bean.OrderBean
import com.dingdian.order.databinding.ItemCompleteOrderBinding
import com.dingdian.order.utils.DeviceUtil.callPhone


class CompleteOrderAdapter :
        BaseQuickAdapter<OrderBean, CompleteOrderAdapter.ViewHolder>(R.layout.item_complete_order) {

    var viewGoods: ((OrderBean) -> Unit)? = null

    override fun convert(helper: ViewHolder, item: OrderBean) {
        helper.binding.model = item
        helper.binding.viewGoods.setOnClickListener {
            viewGoods?.invoke(item)
        }
        helper.binding.userPhone.setOnClickListener {
            callPhone(helper.binding.root.context, item.addressPhone)
        }

        helper.binding.sendPhone.setOnClickListener {
            callPhone(helper.binding.root.context, item.senderPhone)
        }
        helper.binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {

        val binding: ItemCompleteOrderBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemCompleteOrderBinding
    }
}