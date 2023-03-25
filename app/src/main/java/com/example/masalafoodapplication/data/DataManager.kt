package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.Cuisine
import com.example.masalafoodapplication.data.domain.Food

object DataManager {
    private val foodsList = mutableListOf<Food>()

    fun addFood(food: Food) {
        foodsList.add(food)
    }

    fun getAllFood() = foodsList


    fun getRandomQuickRecipes(limit: Int): List<Food> {
        return foodsList
            .filter { it.timeMinutes < 30 }
            .shuffled()
            .take(limit)
    }

    fun getRandomFoods(limit: Int): List<Food> {
        return foodsList
            .shuffled()
            .take(limit)
    }

    fun search(value: String) =
        foodsList.filter {
            it.recipeName.lowercase()
                .contains(value) || it.cleaned.lowercase().contains(value) || it.cuisine.lowercase()
                .contains(value)
        }.shuffled().take(6)

    fun getCuisines(limit: Int): List<Cuisine> {
        return foodsList
            .map { it.cuisine }
            .distinct()
            .map { Cuisine(it, getImageByCuisine(it)) }
            .shuffled()
            .take(limit)
    }

    fun getRandomFoodImage(): String {
        return foodsList
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }

    fun getImageByCuisine(cuisine: String): String {
        return foodsList
            .filter { it.cuisine == cuisine }
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }

}