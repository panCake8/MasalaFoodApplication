package com.example.masalafoodapplication.data.datasource

import android.app.Application
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.util.Constants
import java.io.BufferedReader
import java.io.InputStreamReader

class DataSourceImpl(private val context: Application) : DataSource {

    private fun parseFile(bufferedReader: BufferedReader): MutableList<List<String>> {
        val data = mutableListOf<List<String>>()
        bufferedReader.readLines().forEachIndexed { index, item ->
            val tokens = item.split(",")
            data[index] = tokens
        }
        return data
    }

    private fun openFile(fileName: String): BufferedReader {
        val inputStream = context.assets.open(fileName)
        return BufferedReader(InputStreamReader(inputStream))
    }

    override fun getAllFood(): List<Food> {
        val foods = mutableListOf<Food>()
        val fileReader = openFile("indian_food.csv")
        var id = 0
        parseFile(fileReader).forEach { data ->
            foods.add(
                Food(
                    id = id,
                    recipeName = data[Constants.ColumnIndex.RECIPE_NAME],
                    ingredientQuantities = data[Constants.ColumnIndex.INGREDIENTS].split(";"),
                    timeMinutes = data[Constants.ColumnIndex.TIME_MINUTES].toInt(),
                    cuisine = data[Constants.ColumnIndex.CUISINE],
                    steps = data[Constants.ColumnIndex.MAKE_RECIPE].split(";"),
                    url = data[Constants.ColumnIndex.URL],
                    ingredient = data[Constants.ColumnIndex.CLEANED].split(";"),
                    imageUrl = data[Constants.ColumnIndex.IMAGE_URL],
                    count = data[Constants.ColumnIndex.COUNT].toInt(),
                )
            )
            id++
        }
        fileReader.close()
        return foods
    }

    override fun getRandomFoodImage(): String {
        return getAllFood().shuffled().first().imageUrl
    }

    override fun getRandomQuickRecipes(limit: Int): List<Food> {
        return getAllFood().filter { it.timeMinutes < 30 }.take(limit)
    }

    override fun getCuisines(limit: Int): List<Cuisine> {
        return getAllFood()
            .map { it.cuisine }
            .distinct()
            .map { Cuisine(it, getImageByCuisine(it)) }
            .shuffled()
            .take(limit)
    }

    override fun getRandomFoods(limit: Int): List<Food> {
        return getAllFood()
            .shuffled()
            .take(limit)
    }

    override fun getImageByCuisine(cuisine: String): String {
        return getAllFood()
            .filter { it.cuisine == cuisine }
            .shuffled()
            .take(1)
            .map { it.imageUrl }
            .first()
    }
}