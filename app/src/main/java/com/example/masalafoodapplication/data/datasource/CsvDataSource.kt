package com.example.masalafoodapplication.data.datasource

import android.content.Context
import com.example.masalafoodapplication.data.datasource.utils.CsvParser
import com.example.masalafoodapplication.data.domain.models.Food
import java.io.InputStreamReader

interface MasalaFoodDataSource {
    fun getRecipesData(): List<Food>
}

class CsvDataSource(private val parser: CsvParser, private val context: Context) :
    MasalaFoodDataSource {
    override fun getRecipesData(): List<Food> {
        return getCsvFile().readLines().mapIndexed { index, line ->
            parser.parseLine(line, index)
        }
    }

    private fun getCsvFile(): InputStreamReader {
        val inputStream = context.assets.open(FILE_NAME)
        return (InputStreamReader(inputStream))
    }

    companion object {
        private const val FILE_NAME = "indian_food.csv"
    }
}