package com.example.masalafoodapplication.data

import android.app.Application
import com.example.masalafoodapplication.data.datasource.DataSource
import com.example.masalafoodapplication.data.datasource.DataSourceImpl

class DatasourceProveider {
    companion object {
        private var instance: DataSource? = null
         fun DataSource(context: Application): DataSource {
            return instance ?: DataSourceImpl(context).also {
                instance = it
            }
        }

        fun getDataSource() = instance
    }
}