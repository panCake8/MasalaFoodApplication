package com.example.masalafoodapplication.ui.home

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food


sealed class HomeItem {
    data class Banner(
        val data: String,
        val type: HomeItemType = HomeItemType.BANNER
    ) : HomeItem()

    data class Ramadan2023(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.RAMADAN_2023
    ) : HomeItem()

    data class QuickAndEasy(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.QUICK_AND_EASY
    ) : HomeItem()

    data class Vegetarian(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.VEGETARIAN
    ) : HomeItem()

    data class StepByStep(
        val data: List<Food>,
        val type: HomeItemType = HomeItemType.STEP_BY_STEP
    ) : HomeItem()

    data class PopularCuisines(
        val data: List<Cuisine>,
        val type: HomeItemType = HomeItemType.POPULAR_CUISINES
    ) : HomeItem()

    data class FoodHistory(
        val data: String,
        val type: HomeItemType = HomeItemType.INDIAN_FOOD_HISTORY
    ) : HomeItem()

}