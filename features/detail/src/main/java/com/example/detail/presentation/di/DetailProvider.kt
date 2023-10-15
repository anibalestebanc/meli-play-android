package com.example.detail.presentation.di

import com.example.detail.data.DefaultDetailRepository
import com.example.detail.data.remote.ItemsApi
import com.example.detail.data.remote.RemoteDataSource
import com.example.detail.data.source.DetailDataSource
import com.example.detail.domain.DetailRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DetailProvider {

    val baseURL: String

    val itemsApi: ItemsApi

    val remoteDatasource: DetailDataSource

    val repository: DetailRepository

    companion object {
        fun create(): DetailProvider = DetailProviderDefault()
    }
}

class DetailProviderDefault : DetailProvider {
    override val baseURL: String
        get() = "https://api.mercadolibre.com/"
    override val itemsApi: ItemsApi
        get() = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemsApi::class.java)
    override val remoteDatasource: DetailDataSource
        get() = RemoteDataSource(itemsApi)
    override val repository: DetailRepository
        get() = DefaultDetailRepository(remoteDatasource)

}