package com.dingdian.order

import android.databinding.BindingAdapter
import android.widget.EditText

@BindingAdapter("text")
fun EditText.text(text: String) {
    if (this.text.toString() != text) {
        setText(text)
    }
}