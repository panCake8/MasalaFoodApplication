package com.example.masalafoodapplication.ui.detailsKitchen.adapter

import com.example.masalafoodapplication.data.domain.enums.HomeItemType

data class DetailsKitchenItem<T>(
    val data: T,
    val type: DetailsItemType,
)