package com.example.masalafoodapplication.data

import android.app.Application
import android.content.Context
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment
import com.example.masalafoodapplication.util.CsvParser
import java.io.InputStreamReader

class DataManagerImpl(private val parser: CsvParser, private val context: Application) : DataManager {

    private val favouriteFoodList = mutableListOf<Food>()

    private fun getCsvFile(): InputStreamReader {
        val inputStream = context.assets.open("indian_food.csv")
        return (InputStreamReader(inputStream))
    }

    override fun getAllFood(): List<Food> {
        var id = 0
        return getCsvFile().readLines().map {
            parser.parse(it, id++)
        }
    }

    override fun getRandomQuickRecipes(limit: Int): List<Food> {
        return getAllFood()
            .filter { it.timeMinutes < 30 }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoods(limit: Int): List<Food> {
        return getAllFood()
            .shuffled()
            .take(limit)
    }

    override fun search(value: String) =
        getAllFood().take(500).filter {
            it.recipeName.lowercase().contains(value)
        }

    override fun getCuisines(limit: Int): List<Cuisine> {
        return getAllFood()
            .map { it.cuisine }
            .distinct()
            .map { Cuisine(it, getImageByCuisine(it)) }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoodImage(): String {
        return getAllFood()
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }

    override fun getImageByCuisine(cuisine: String): String {
        return getAllFood()
            .filter { it.cuisine == cuisine }
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }


    fun getIngredients(limit: Int): List<String> {
        val ingredientToFilter = mutableListOf<String>()
        getAllFood().forEach { food ->
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
        return if (meal == SuggestionsFragment.BREAKFAST || meal == SuggestionsFragment.DINNER) {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes < 30 }
        } else {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes > 30 }
        }
    }

    override fun getAllQuickRecipes() = getAllFood().filter { it.timeMinutes < 30 }

    override fun getFoodById(id: Int) = getAllFood().first { it.id == id }

    override fun getRecipesByCuisine(cuisine: String): List<Food> {
        return getAllFood().filter { it.cuisine == cuisine }
    }

    override fun filterData(
        kitchens: List<String>?,
        ingredient: List<String>?,
        time: Float
    ) =
        getAllFood().take(500).filter {
            kitchens?.contains(it.cuisine) == true
                    || ingredient?.containsAll(it.ingredient) == true
                    || it.timeMinutes == time.toInt()
        }

    override fun getAllFavouriteFood() = favouriteFoodList.toList()

    override fun addFavourite(food: Food) {
        favouriteFoodList.add(food)
    }

    override fun isFavorite(food: Food): Boolean {
        return favouriteFoodList.any { it == food }
    }

    override fun deleteFavourite(index: Int) {
        favouriteFoodList.removeAt(index)
    }

    override fun deleteFavourite(food: Food) {
        favouriteFoodList.remove(food)
    }

}