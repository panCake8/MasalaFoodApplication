package com.example.masalafoodapplication.data.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: Int,
    val recipeName: String,
    val ingredientQuantities: List<String>,
    val timeMinutes: Int,
    val cuisine: String,
    val steps: List<String>,
    val url: String,
    val ingredient: List<String>,
    val imageUrl: String,
    val count: Int,
) : Parcelable