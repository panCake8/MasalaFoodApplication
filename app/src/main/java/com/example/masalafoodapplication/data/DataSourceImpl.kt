package com.example.masalafoodapplication.data

import android.content.Context
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.util.CsvParser
import java.io.InputStreamReader

class DataSourceImpl(private val parser: CsvParser, private val context: Context) : DataSource {
    private fun getCsvFile(): InputStreamReader {
        val inputStream = context.assets.open("indian_food.csv")
        return (InputStreamReader(inputStream))
    }

    override fun getAllFood(): List<Food> {
        var id=0
        return getCsvFile().readLines().map {
            parser.parse(it, id++)
        }
    }
}