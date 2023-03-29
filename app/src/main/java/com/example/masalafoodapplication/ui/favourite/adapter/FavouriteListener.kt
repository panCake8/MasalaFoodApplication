package com.example.masalafoodapplication.ui.favourite.adapter

import com.example.masalafoodapplication.data.domain.models.Food


interface FavouriteListener {
    fun onClickHeart(position: Int)
    fun onClickItem(food: Food)
}