package com.example.masalafoodapplication.data.domain.models

import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType

data class FoodDetailsItem<T>(
    val data: T,
    val type: FoodDetaisType,
)
