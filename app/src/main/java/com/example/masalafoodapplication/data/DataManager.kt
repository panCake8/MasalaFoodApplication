package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import java.io.Serializable

interface DataManager : Serializable {

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

    fun getRecipesByCuisine(cuisine: String, limit: Int): List<Food>

    fun filterData(kitchens: List<String>?, ingredient: List<String>?, time: Float): List<Food>

    fun getAllFavouriteFood(): List<Food>

    fun addFavourite(food: Food)

    fun isFavorite(food: Food): Boolean

    fun deleteFavourite(index: Int)

    fun deleteFavourite(food: Food)

    fun getIngredients(limit: Int): List<String>

    fun getVegetarianRecipes(limit: Int): List<Food>

    fun getMostRichCuisines(limit: Int): List<Cuisine>

    fun getMostStepsRecipes(limit: Int): List<Food>
}