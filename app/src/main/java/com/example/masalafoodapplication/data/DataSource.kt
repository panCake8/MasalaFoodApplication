package com.example.masalafoodapplication.data

import com.example.masalafoodapplication.data.domain.models.Food

interface DataSource {
    fun getAllFood(): List<Food>
}