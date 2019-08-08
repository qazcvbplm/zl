package com.dingdian.order.ui.comment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dingdian.order.R
import com.dingdian.order.bean.CommentBean
import com.dingdian.order.databinding.ItemCommentListBinding

class CommentListAdapter : BaseQuickAdapter<CommentBean, CommentListAdapter.ViewHolder>(R.layout.item_comment_list) {
    override fun convert(helper: ViewHolder, item: CommentBean) {
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

        val binding: ItemCommentListBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemCommentListBinding
    }
}