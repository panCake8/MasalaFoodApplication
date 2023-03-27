package com.example.masalafoodapplication.util

import com.example.masalafoodapplication.data.domain.models.Food

class CsvParser {
    fun parse(line: String, id: Int): Food {
        val tokens = line.split(",")
        return Food(
            id = id,
            recipeName = tokens[Constants.ColumnIndex.RECIPE_NAME],
            ingredientQuantities = tokens[Constants.ColumnIndex.INGREDIENTS].split(";"),
            timeMinutes = tokens[Constants.ColumnIndex.TIME_MINUTES].toInt(),
            cuisine = tokens[Constants.ColumnIndex.CUISINE],
            steps = tokens[Constants.ColumnIndex.MAKE_RECIPE].split(";"),
            url = tokens[Constants.ColumnIndex.URL],
            ingredient = tokens[Constants.ColumnIndex.CLEANED].split(";"),
            imageUrl = tokens[Constants.ColumnIndex.IMAGE_URL],
            count = tokens[Constants.ColumnIndex.COUNT].toInt(),
        )
    }
}