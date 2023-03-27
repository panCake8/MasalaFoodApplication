package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.util.Constants

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
        foodsList.take(500).filter {
            it.recipeName.lowercase().contains(value)
        }

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


    fun getIngredients(limit: Int): List<String> {
        val ingredientToFilter = mutableListOf<String>()
        foodsList.forEach { food ->
            food.ingredient
                .forEach {
                    if (!ingredientToFilter.contains(it))
                        ingredientToFilter.add(it)
                }
        }
        return ingredientToFilter.take(limit)
    }


    private fun searchFoodsAccordingSuggestions(recipes: List<String>) =
        getAllFood()
            .filter {
                it.ingredient
                    .containsAll(recipes)
            }

    override fun splitFoodsIntoThreeMeals(meal: String, recipes: List<String>): List<Food> {
        return if (meal == Constants.BREAKFAST || meal == Constants.DINNER) {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes < 30 }
        } else {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes > 30 }
        }
    }

    override fun getAllQuickRecipes() = foodsList.filter { it.timeMinutes < 30 }

    override fun getFoodById(id: Int) = foodsList.first { it.id == id }

    override fun getRecipesByCuisine(cuisine: String): List<Food> {
        return foodsList.filter { it.cuisine == cuisine }
    }

}