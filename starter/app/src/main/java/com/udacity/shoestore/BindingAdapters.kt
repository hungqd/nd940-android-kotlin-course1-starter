package com.udacity.shoestore

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("app:text")
fun setDoubleToTextView(tv: TextView, value: Double?) {
    if (value != null && value != 0.0 && tv.text.isBlank()) {
        tv.text = value.toString()
    }
}

@InverseBindingAdapter(attribute = "app:text", event = "android:textAttrChanged")
fun getTextDouble(view: TextView): Double {
    return view.text.toString().toDoubleOrNull() ?: .0
}