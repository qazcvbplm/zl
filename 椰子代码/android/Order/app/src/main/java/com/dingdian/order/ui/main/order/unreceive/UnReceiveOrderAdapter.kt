package com.dingdian.order.ui.main.order.unreceive

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dingdian.order.R
import com.dingdian.order.bean.OrderBean
import com.dingdian.order.databinding.ItemUnReceiveOrderBinding
import com.dingdian.order.utils.DeviceUtil

class UnReceiveOrderAdapter : BaseQuickAdapter<OrderBean, UnReceiveOrderAdapter.ViewHolder>(R.layout.item_un_receive_order) {

    var callback: ((OrderBean) -> Unit)? = null
    var viewGoods: ((OrderBean) -> Unit)? = null

    override fun convert(helper: ViewHolder, item: OrderBean) {
        helper.binding.model = item
        helper.binding.receiveBtn.setOnClickListener {
            callback?.invoke(item)
        }

        helper.binding.viewGoods.setOnClickListener {
            viewGoods?.invoke(item)
        }

        helper.binding.userPhone.setOnClickListener {
            DeviceUtil.callPhone(helper.binding.root.context, item.addressPhone)
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

        val binding: ItemUnReceiveOrderBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemUnReceiveOrderBinding
    }
}