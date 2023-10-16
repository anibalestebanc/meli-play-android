package com.example.detail.domain.model

data class ItemDetail(
    val id: String,
    val title: String,
    val price: Int,
    val originalPrice: Int,
    val officialStoreName: String?,
    val categoryId: String,
    val availableQuantity: Int,
    val thumbnail: String,
    val pictures: List<ItemPicture>
)
