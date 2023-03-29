package com.example.masalafoodapplication.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_cloud_download_24)
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(this)
}

fun Int.setTime(): String {
    return when (this) {
        in 0..60 -> "$this min"
        else -> (this / 60).toString() + " hr " + (this % 60).toString() + " min"
    }
}


