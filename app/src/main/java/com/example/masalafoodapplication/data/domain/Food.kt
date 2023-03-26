package com.example.masalafoodapplication.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val recipeName: String,
    val ingredients: String,
    val timeMinutes: Int,
    val cuisine: String,
    val makeRecipe: String,
    val url: String,
    val cleaned: String,
    val imageUrl: String,
    val count: Int,
) : Parcelable