package com.example.search.domain.model

data class Item(
    val id: String,
    val title: String,
    val price: Int,
    val originalPrice: Int,
    val officialStoreName: String?,
    val categoryId: String,
    val availableQuantity: Int,
    val thumbnail: String
)
