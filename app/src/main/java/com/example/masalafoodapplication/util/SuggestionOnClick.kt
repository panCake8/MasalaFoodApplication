package com.example.masalafoodapplication.util

import com.example.masalafoodapplication.data.domain.models.Food

interface SuggestionOnClick{
    fun onClickListener(food: Food)
}