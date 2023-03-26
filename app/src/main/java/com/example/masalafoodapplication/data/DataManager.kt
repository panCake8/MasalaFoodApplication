package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.Food

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


    fun showMostQuickRecipes() = foodsList.sortedBy {
        it.timeMinutes
    }

    fun showJustForYou() = foodsList.shuffled()


    private fun searchFoodsAccordingSuggestions(recipes: List<String>) =
        getAllFood()
        .filter{ it.cleaned.split(";").containsAll(recipes) }

    fun splitFoodsIntoThreeMeals(meal:String,recipes: List<String>):List<Food>{
        return if (meal == Constants.BREAKFAST || meal == Constants.DINNER){
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes < 30 }
        }else{
            searchFoodsAccordingSuggestions(recipes).filter { it.timeMinutes > 30 }
        }
    }


}