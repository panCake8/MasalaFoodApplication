package com.example.masalafoodapplication.util

import com.kiko.fillapp.data.domain.Food

class CsvParser {
    fun parse(line: String): Food {
        val tokens = line.split(",")
        return Food(
            recipeName = tokens[Constants.ColumnIndex.RECIPE_NAME],
            ingredients = tokens[Constants.ColumnIndex.INGREDIENTS],
            timeMinutes = tokens[Constants.ColumnIndex.TIME_MINUTES],
            Cuisine = tokens[Constants.ColumnIndex.CUISINE],
            makeRecipe = tokens[Constants.ColumnIndex.MAKE_RECIPE],
            url = tokens[Constants.ColumnIndex.URL],
            cleaned = tokens[Constants.ColumnIndex.CLEANED],
            imageUrl = tokens[Constants.ColumnIndex.IMAGE_URL],
            count = tokens[Constants.ColumnIndex.COUNT],
        )
    }
}