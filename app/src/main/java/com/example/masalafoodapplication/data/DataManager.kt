package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.Cuisine
import com.example.masalafoodapplication.data.domain.Food

object DataManager : BaseDataManager {
    private val foodsList = mutableListOf<Food>()

    override fun addFood(food: Food) {
        foodsList.add(food)
    }

    override fun getAllFood() = foodsList

    override fun getRandomQuickRecipes(limit: Int): List<Food> {
        return foodsList
            .filter { it.timeMinutes < 30 }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoods(limit: Int): List<Food> {
        return foodsList
            .shuffled()
            .take(limit)
    }

    override fun search(value: String) =
        foodsList.filter {
            it.recipeName.lowercase()
                .contains(value) || it.cleaned.lowercase().contains(value) || it.cuisine.lowercase()
                .contains(value)
        }.toList()

    override fun getCuisines(limit: Int): List<Cuisine> {
        return foodsList
            .map { it.cuisine }
            .distinct()
            .map { Cuisine(it, getImageByCuisine(it)) }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoodImage(): String {
        return foodsList
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }

    override fun getImageByCuisine(cuisine: String): String {
        return foodsList
            .filter { it.cuisine == cuisine }
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }


    fun ingredientFilter(): MutableList<String> {
        val ingredientToFilter = mutableListOf<String>()
        foodsList.forEach { food ->
            food.cleaned.split(";").forEach {
                if (!ingredientToFilter.contains(it))
                    ingredientToFilter.add(it)
            }
        }
        return (ingredientToFilter.subList(1, 60))
    }

}