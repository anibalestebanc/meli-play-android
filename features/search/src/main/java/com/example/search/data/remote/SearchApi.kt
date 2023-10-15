package com.example.search.data.remote

import com.example.search.data.remote.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("sites/{site_id}/search")
    suspend fun getSearchByText(
        @Path("site_id") site : String,
        @Query("q") value: String
    ): SearchResponse
}