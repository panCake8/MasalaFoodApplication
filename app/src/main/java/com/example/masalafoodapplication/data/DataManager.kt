package com.example.masalafoodapplication.data

import com.kiko.fillapp.data.domain.Food

object DataManager {
    private val foodsList = mutableListOf<Food>()

    fun addFood(food: Food) {
        foodsList.add(food)
    }

    fun getAllFood() = foodsList


    fun getRandomQuickRecipes(limit: Int) = foodsList.filter {
        it.timeMinutes < 30
    }.shuffled().take(limit)

    fun getRandomFoods(limit: Int) = foodsList.shuffled().take(limit)

    fun getRandomImageUrlByCuisine(cuisine: String) =
        foodsList.filter { it.cuisine == cuisine }.shuffled().take(1).map { it.imageUrl }.first()

    fun search(value: String) =
        foodsList.filter {
            it.recipeName.lowercase()
                .contains(value) || it.cleaned.lowercase().contains(value) || it.cuisine.lowercase()
                .contains(value)
        }.shuffled().take(6)

}