package com.example.masalafoodapplication.ui.home

import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {
    fun onBannerClicked()

    fun onRecipeClicked(food: Food)

    fun onCuisineClicked(cuisine: Cuisine)

    fun onIndianFoodHistoryClicked()

    fun onSeeMoreClicked(type: HomeItemType)
}