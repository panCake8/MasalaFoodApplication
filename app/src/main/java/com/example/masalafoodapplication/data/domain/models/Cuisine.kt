package com.example.masalafoodapplication.data.domain.models

data class Cuisine(
    val name: String,
    val imageUrl: String,
    val recipesCount: Int = 0
)
