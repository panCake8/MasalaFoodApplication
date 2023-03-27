package com.example.masalafoodapplication.ui.home.adapters

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.enums.HomeItemType

interface HomeInteractionListener {
    fun onBannerClicked()

    fun onRecipeClicked(food: Food)

    fun onCuisineClicked(cuisine: Cuisine)

    fun onIndianFoodHistoryClicked()

    fun onSeeMoreClicked(type: HomeItemType)
}