package com.example.masalafoodapplication.ui.home

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food


sealed class HomeItem {
    data class Banner(
        val data: String,
        val type: HomeItemType = HomeItemType.BANNER
    ) : HomeItem()

    data class QuickRecipes(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.QUICK_RECIPES
    ) : HomeItem()

    data class JustForYou(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.JUST_FOR_YOU
    ) : HomeItem()

    data class Cuisines(val data: List<Cuisine>, val type: HomeItemType = HomeItemType.CUISINES) :
        HomeItem()

    data class FoodHistory(
        val data: String,
        val type: HomeItemType = HomeItemType.INDIAN_FOOD_HISTORY
    ) : HomeItem()

}