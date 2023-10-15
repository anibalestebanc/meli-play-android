package com.example.detail.data.remote

import com.example.detail.data.remote.model.ResponseDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsApi {
    @GET("items")
    suspend fun getItemById(
        @Query("ids") value: String
    ): List<ResponseDetail>
}