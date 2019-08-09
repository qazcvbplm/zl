package com.dingdian.order.ui.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import com.dingdian.order.databinding.DialogGeneralLayoutBinding
import com.dingdian.order.visibleOrGone

class GeneralDialog(context: Context) : Dialog(context) {

    val binding by lazy { DialogGeneralLayoutBinding.inflate(LayoutInflater.from(context)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.progressBar.max = 100
    }

    fun setCannotCancel(): GeneralDialog {
        setCancelable(false)
        return this
    }

    fun setImage(resId: Int): GeneralDialog {
        binding.image.setImageResource(resId)
        binding.image.visibleOrGone = true
        return this
    }

    fun setTitle(title: String): GeneralDialog {
        binding.title.text = title
        binding.title.visibleOrGone = true
        return this
    }

    fun setMessage(message: String): GeneralDialog {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.message.text = Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.message.text = Html.fromHtml(message)
        }
//        binding.message.text = message
        binding.message.visibleOrGone = true
        return this
    }

    fun setUpdateMessage(message: String): GeneralDialog {
        binding.message.text = message
        binding.message.visibleOrGone = true
        return this
    }

    fun setEdtPwd(): GeneralDialog {
        binding.llEdtPwd.visibleOrGone = true
        return this
    }

    fun setEyeBtn(callback: (Dialog) -> Unit): GeneralDialog {
        binding.ivEye.setOnClickListener {
            callback.invoke(this)
        }
        return this
    }


    fun setCheckBox(callback: (Dialog) -> Unit): GeneralDialog {
        binding.ivCheckBox.setOnClickListener {
            callback.invoke(this)
        }
        binding.checkbox.visibleOrGone = true
        return this
    }

    fun setProgress(progress: Int): GeneralDialog {
        binding.progressBar.progress = progress
        binding.progressBar.visibleOrGone = true
        return this
    }


    fun setNegativeBtn(text: String, callback: (Dialog) -> Unit): GeneralDialog {
        binding.negativeBtn.text = text
        binding.negativeBtn.setOnClickListener {
            callback.invoke(this)
        }
        binding.negativeBtn.visibleOrGone = !text.isNullOrBlank()
        return this
    }

    fun setNegativeEnable(isEnabled: Boolean): GeneralDialog {
        binding.negativeBtn.isEnabled = isEnabled
        return this
    }


    fun setPositiveBtn(text: String, callback: (Dialog) -> Unit): GeneralDialog {
        binding.positiveBtn.text = text
        binding.positiveBtn.setOnClickListener {
            callback.invoke(this)
        }
        binding.positiveBtn.visibleOrGone = !text.isNullOrBlank()
        return this
    }

    fun setPositiveEnable(isEnabled: Boolean): GeneralDialog {
        binding.positiveBtn.isEnabled = isEnabled
        return this
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding.unbind()
    }


}