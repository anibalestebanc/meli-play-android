package com.example.detail.data.remote.model

import com.example.detail.domain.model.ItemDetail
import com.example.detail.domain.model.ItemPicture
import com.google.gson.annotations.SerializedName


data class ResponseDetail(
    @SerializedName("body")
    val body: RemoteItem,
    @SerializedName("code")
    val code: Int
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
    @SerializedName("pictures")
    val pictures: List<RemotePictures>
)

data class RemotePictures(
    @SerializedName("url")
    val url: String,
    @SerializedName("secure_url")
    val secureUrl: String
)

fun RemoteItem.asDomain() = ItemDetail(
    id = id,
    title = title,
    price = price,
    originalPrice = originalPrice,
    officialStoreName = officialStoreName,
    categoryId = categoryId,
    availableQuantity = availableQuantity,
    thumbnail = thumbnail,
    pictures = pictures.map { it.asDomain() }
)

fun RemotePictures.asDomain() = ItemPicture(
    url = url,
    secureUrl = secureUrl
)
