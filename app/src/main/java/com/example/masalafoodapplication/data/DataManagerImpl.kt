package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.datasource.MasalaFoodDataSource
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment

class DataManagerImpl(dataSource: MasalaFoodDataSource) : DataManager {

    private val recipesData = dataSource.getRecipesData()

    private val favouriteFoodList = mutableListOf<Food>()

    override fun getAllFood(): List<Food> {
        return recipesData
    }

    override fun getRandomQuickRecipes(limit: Int): List<Food> {
        return recipesData
            .filter { it.timeMinutes < 30 }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoods(limit: Int): List<Food> {
        return recipesData
            .shuffled()
            .take(limit)
    }

    override fun search(value: String) =
        recipesData.take(500).filter {
            it.recipeName.lowercase().contains(value)
        }

    override fun getCuisines(limit: Int): List<Cuisine> {
        return recipesData
            .map { it.cuisine }
            .distinct()
            .map { Cuisine(it, getImageByCuisine(it)) }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoodImage(): String {
        return recipesData
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }

    override fun getImageByCuisine(cuisine: String): String {
        return recipesData
            .filter { it.cuisine == cuisine }
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }


    override fun getIngredients(limit: Int): List<String> {
        return recipesData
            .flatMap { it.ingredient }
            .distinct()
            .take(limit)
    }


    private fun searchFoodsAccordingSuggestions(recipes: List<String>) =
        recipesData.filter { it.ingredient.containsAll(recipes) }

    override fun splitFoodsIntoThreeMeals(meal: String, recipes: List<String>): List<Food> {
        return if (meal == SuggestionsFragment.BREAKFAST || meal == SuggestionsFragment.DINNER) {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes < 30 }
        } else {
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes >= 30 }
        }
    }

    override fun getAllQuickRecipes() = recipesData.filter { it.timeMinutes < 30 }

    override fun getFoodById(id: Int) = recipesData.first { it.id == id }

    override fun getRecipesByCuisine(cuisine: String, limit: Int): List<Food> {
        return recipesData.filter { it.cuisine == cuisine }.take(limit)
    }

    override fun filterData(
        kitchens: List<String>?,
        ingredient: List<String>?,
        time: Float
    ) =
        recipesData.take(500).filter {
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

    override fun getMostRichCuisines(limit: Int): List<Cuisine> {
        return recipesData
            .groupBy { it.cuisine }
            .map { Cuisine(it.key, getImageByCuisine(it.key), it.value.size) }
            .sortedByDescending { it.recipesCount }
            .take(limit)
    }

    override fun getVegetarianRecipes(limit: Int): List<Food> {
        val notAllowedIngredients = listOf("egg", "meat", "fish", "chicken", "beef", "pork", "lamb")
        return recipesData.shuffled().take(500).filter { food ->
            food.ingredient.none { ingredient ->
                notAllowedIngredients.any { ingredient.lowercase().contains(it) }
            }
        }
    }

    override fun getMostStepsRecipes(limit: Int): List<Food> {
        return recipesData.shuffled().take(500).sortedByDescending { it.steps.size }.take(limit)
    }
}