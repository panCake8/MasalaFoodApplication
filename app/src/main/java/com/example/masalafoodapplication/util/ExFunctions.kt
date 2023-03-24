package com.example.masalafoodapplication.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.ic_baseline_cloud_download_24)
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(this)
}

@SuppressLint("SetTextI18n")
fun TextView.setPreparationTime(time: Int) {
    this.text = "Prep Time: $time minutes"
}