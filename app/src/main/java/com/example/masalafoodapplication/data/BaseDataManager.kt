package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.Cuisine
import com.example.masalafoodapplication.data.domain.Food

interface BaseDataManager {

    fun addFood(food: Food)
    fun getAllFood(): List<Food>
    fun getRandomQuickRecipes(limit: Int): List<Food>
    fun getRandomFoods(limit: Int): List<Food>
    fun search(value: String): List<Food>
    fun getCuisines(limit: Int): List<Cuisine>
    fun getRandomFoodImage(): String
    fun getImageByCuisine(cuisine: String): String

}