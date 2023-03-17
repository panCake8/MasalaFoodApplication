package com.example.masalafoodapplication.data

import com.kiko.fillapp.data.domain.Food

object DataManager {
    private val foodsList = mutableListOf<Food>()

    fun addFood(food: Food) {
        foodsList.add(food)
    }

    fun getAllFood() = foodsList


    fun search(value: String) =
        foodsList.filter {
            it.timeMinutes == value
        }
}