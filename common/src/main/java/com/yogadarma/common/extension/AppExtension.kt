package com.yogadarma.common.extension

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: Any) {
    Glide.with(this.context).load(image).into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Parcelable> Bundle.getParcelableData(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}