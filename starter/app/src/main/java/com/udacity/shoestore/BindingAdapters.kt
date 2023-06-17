package com.udacity.shoestore

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:text")
fun setDoubleToTextView(tv: TextView, value: Double?) {
    if (value != null && value != 0.0 && tv.text.isBlank()) {
        tv.text = value.toString()
    }
}