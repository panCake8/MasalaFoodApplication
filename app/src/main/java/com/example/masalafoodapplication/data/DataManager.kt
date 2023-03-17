package com.example.masalafoodapplication.data

import com.kiko.fillapp.data.domain.Food

object DataManager {
    private val foodsList = mutableListOf<Food>()
    fun addFood(food: Food) {
        foodsList.add(food)
    }

    fun getFood() = foodsList


    fun search(value: String) =
        foodsList.filter {
            it.timeMinutes == value
        }

    fun getAllFood(): List<Food> = foodsList
}