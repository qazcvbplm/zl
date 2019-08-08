package com.dingdian.order

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.*

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else INVISIBLE
    }

@BindingAdapter("selected")
fun View.selected(selected: Boolean) {
    isSelected = selected
}