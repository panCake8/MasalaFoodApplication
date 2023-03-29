package com.example.masalafoodapplication.data.datasource.utils

import com.example.masalafoodapplication.data.domain.models.Food

class CsvParser {
    fun parseLine(line: String, id: Int): Food {
        val tokens = line.split(",")
        return Food(
            id = id,
            recipeName = tokens[ColumnIndex.RECIPE_NAME],
            ingredientQuantities = tokens[ColumnIndex.INGREDIENTS].split(";"),
            timeMinutes = tokens[ColumnIndex.TIME_MINUTES].toInt(),
            cuisine = tokens[ColumnIndex.CUISINE],
            steps = tokens[ColumnIndex.MAKE_RECIPE].split(";"),
            url = tokens[ColumnIndex.URL],
            ingredient = tokens[ColumnIndex.CLEANED].split(";"),
            imageUrl = tokens[ColumnIndex.IMAGE_URL],
            count = tokens[ColumnIndex.COUNT].toInt(),
        )
    }
}