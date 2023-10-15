package com.example.detail.data.remote.model

import com.example.detail.domain.model.ItemDetail
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
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
)

fun RemoteItem.asDomain() = ItemDetail(
    id = id
)