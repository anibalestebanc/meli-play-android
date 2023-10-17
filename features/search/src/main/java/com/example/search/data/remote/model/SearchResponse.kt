package com.example.search.data.remote.model

import com.example.search.domain.model.Item
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val result: List<RemoteItem>
)
data class RemoteItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("original_price")
    val originalPrice: Int,
    @SerializedName("official_store_name")
    val officialStoreName: String?,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
)

fun RemoteItem.asDomain() = Item(
    id = id,
    title = title,
    price = price,
    originalPrice = originalPrice,
    officialStoreName = officialStoreName,
    categoryId = categoryId,
    availableQuantity = availableQuantity,
    thumbnail = thumbnail
)