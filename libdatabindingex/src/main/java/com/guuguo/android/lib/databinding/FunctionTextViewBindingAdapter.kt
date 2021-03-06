package com.guuguo.android.lib.databinding

import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import com.guuguo.android.lib.widget.FunctionTextView


@BindingMethods(BindingMethod(type = FunctionTextView::class, attribute = "android:text", method = "setText"),
        BindingMethod(type = ImageView::class, attribute = "android:src", method = "setImageResource")
)
class FunctionTextViewBindingAdapter

// 根据View的高度和宽高比，设置高度
@BindingAdapter("heightWidthRatio")
fun View.setWidthHeightRatio(ratio: Double) {
    doOnLayout {
        val width = this@setWidthHeightRatio.width
        if (width > 0) {
            this@setWidthHeightRatio.layoutParams.height = (width * ratio).toInt()
        }
    }
}

// 设置高度
@BindingAdapter("app:height")
fun View.setHeight(height: Int) {
    this.updateLayoutParams {
        this.height=height
    }
}
// 设置宽度
@BindingAdapter("app:width")
fun View.setWidth(width: Int) {
    this.updateLayoutParams {
        this.width=width
    }
}