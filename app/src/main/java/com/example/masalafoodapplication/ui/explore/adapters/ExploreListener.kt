package com.example.masalafoodapplication.ui.explore.adapters

import com.example.masalafoodapplication.data.domain.models.Food

interface ExploreListener {
    fun onClickItem(food: Food)
}