package com.example.masalafoodapplication.data.repo

import com.example.masalafoodapplication.data.datasource.DataSource
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun getAllFood(): List<Food> {
        return dataSource.getAllFood()
    }

    override fun getRandomFoodImage(): String {
        return dataSource.getRandomFoodImage()
    }

    override fun getRandomQuickRecipes(limit: Int): List<Food> {
        return dataSource.getRandomQuickRecipes(limit)
    }

    override fun getCuisines(limit: Int): List<Cuisine> {
        return dataSource.getCuisines(limit)
    }

    override fun getRandomFoods(limit: Int): List<Food> {
        return dataSource.getRandomFoods(limit)
    }

    override fun getImageByCuisine(cuisine: String): String {
        return dataSource.getImageByCuisine(cuisine)
    }
}