package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment

interface BaseDataManager {

    fun addFood(food: Food)

    fun getAllFood(): List<Food>

    fun getRandomQuickRecipes(limit: Int): List<Food>

    fun getRandomFoods(limit: Int): List<Food>

    fun search(value: String): List<Food>

    fun getCuisines(limit: Int): List<Cuisine>

    fun getRandomFoodImage(): String

    fun getImageByCuisine(cuisine: String): String

    fun splitFoodsIntoThreeMeals(meal: String, recipes: List<String>): List<Food>

    fun getAllQuickRecipes(): List<Food>

    fun getFoodById(id: Int): Food

    fun getRecipesByCuisine(cuisine: String): List<Food>

    fun filterData(kitchens: List<String>?, ingredient: List<String>?, time: Float): List<Food>
}