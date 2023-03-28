package com.example.masalafoodapplication.data.domain.models

import com.example.masalafoodapplication.data.domain.enums.HomeItemType

data class HomeItem<T>(
    val data: T,
    val type: HomeItemType,
)