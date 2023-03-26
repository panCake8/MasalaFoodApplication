package com.example.masalafoodapplication.ui.home

import com.example.masalafoodapplication.data.domain.Cuisine
import com.example.masalafoodapplication.data.domain.Food
import com.example.masalafoodapplication.data.domain.enums.HomeItemType

interface HomeInteractionListener {
    fun onBannerClicked()

    fun onRecipeClicked(food: Food)

    fun onCuisineClicked(cuisine: Cuisine)

    fun onIndianFoodHistoryClicked()

    fun onSeeMoreClicked(type: HomeItemType)
}