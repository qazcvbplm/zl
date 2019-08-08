package com.dingdian.order.ui.goods

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dingdian.order.bean.GoodsAttr
import com.dingdian.order.databinding.ItemGoodsAttrBinding
import com.dingdian.order.event.RemoveAttrEvent
import org.greenrobot.eventbus.EventBus

class AttrListAdapter(val context: Context) : BaseAdapter() {

    var list = arrayListOf<GoodsAttr>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = if (convertView == null) {
            ItemGoodsAttrBinding.inflate(LayoutInflater.from(context))
        } else {
            DataBindingUtil.bind(convertView)!!
        }

        binding.attr = list[position]
        binding.deleteBtn.setOnClickListener {
            EventBus.getDefault().post(RemoveAttrEvent(position))
        }

        binding.executePendingBindings()
        return binding.root
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

}