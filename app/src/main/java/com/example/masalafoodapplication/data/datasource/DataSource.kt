package com.example.masalafoodapplication.data.datasource

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food

interface DataSource {
    fun getAllFood():List<Food>
    fun getRandomFoodImage(): String
    fun getRandomQuickRecipes(limit: Int): List<Food>
    fun getCuisines(limit: Int): List<Cuisine>
    fun getRandomFoods(limit: Int): List<Food>
    fun getImageByCuisine(cuisine: String): String
}