package com.example.search.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("results")
    val result : List<RemoteItem>
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
