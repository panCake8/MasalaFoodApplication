package com.example.masalafoodapplication

import android.app.Application
import com.example.masalafoodapplication.data.DatasourceProveider

class App : Application() {
    fun getDataSource() = DatasourceProveider.DataSource(this)
}