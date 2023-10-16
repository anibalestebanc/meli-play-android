package com.example.detail.presentation.di

import com.example.detail.data.DefaultDetailRepository
import com.example.detail.data.remote.ItemsApi
import com.example.detail.data.remote.DetailRemoteDataSource
import com.example.detail.domain.DetailRepository
import com.example.network.ApiServiceFactory
import com.example.network.DEFAULT_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DetailProvider {

    val itemsApi: ItemsApi

    val remoteDatasource: DetailRemoteDataSource

    val repository: DetailRepository

    companion object {
        fun create(): DetailProvider = DetailProviderDefault()
    }
}

class DetailProviderDefault : DetailProvider {
    override val itemsApi: ItemsApi
        get() = ApiServiceFactory<ItemsApi>().create(
            baseURL = DEFAULT_BASE_URL,
            kClass = ItemsApi::class.java
        )
    override val remoteDatasource: DetailRemoteDataSource
        get() = DetailRemoteDataSource(itemsApi)
    override val repository: DetailRepository
        get() = DefaultDetailRepository(remoteDatasource)

}