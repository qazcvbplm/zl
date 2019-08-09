package com.dingdian.order

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.dingdian.order.di.module.GlideApp

@BindingAdapter("resId")
fun ImageView.setResId(resId: Int) {
    setImageResource(resId)
}

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        GlideApp.with(context).load(it).into(this)
    }
}