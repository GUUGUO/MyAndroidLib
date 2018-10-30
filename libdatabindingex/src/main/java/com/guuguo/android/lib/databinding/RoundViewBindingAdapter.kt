package com.guuguo.android.lib.databinding

import androidx.databinding.BindingAdapter
import com.guuguo.android.lib.widget.roundview.*


@BindingAdapter("rv_backgroundColor")
fun RoundTextView.setRvBackGroundColor(color: Int) {
    this.delegate.backgroundColor = color
}
@BindingAdapter("rv_strokeColor")
fun RoundTextView.setRvStrokeColor(color: Int) {
    this.delegate.strokeColor = color
}

@BindingAdapter("rv_backgroundColor")
fun RoundBgImageView.setRvBackGroundColor(color: Int) {
    this.delegate.backgroundColor = color
}

@BindingAdapter("rv_backgroundColor")
fun RoundConstraintlayout.setRvBackGroundColor(color: Int) {
    this.delegate.backgroundColor = color
}

@BindingAdapter("rv_backgroundColor")
fun RoundLinearLayout.setRvBackGroundColor(color: Int) {
    this.delegate.backgroundColor = color
}

@BindingAdapter("rv_backgroundColor")
fun RoundFrameLayout.setRvBackGroundColor(color: Int) {
    this.delegate.backgroundColor = color
}
