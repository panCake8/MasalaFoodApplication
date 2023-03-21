package com.example.masalafoodapplication.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(android.R.drawable.progress_horizontal)
        .into(this)
}

@SuppressLint("SetTextI18n")
fun TextView.setPreparationTime(time: Int) {
    this.text = "Prep Time: $time minutes"
}