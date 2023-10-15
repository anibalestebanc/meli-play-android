package com.example.search.presentation.di

import com.example.network.ApiServiceFactory
import com.example.network.DEFAULT_BASE_URL
import com.example.search.data.DefaultSearchRepository
import com.example.search.data.remote.RemoteDataSource
import com.example.search.data.remote.SearchApi
import com.example.search.data.source.SearchDatasource
import com.example.search.domain.repository.SearchRepository
import com.example.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface SearchProvider {

    val searchApi: SearchApi

    val remoteDataSource: SearchDatasource

    val repository: SearchRepository

    val useCase: SearchUseCase
    companion object {
        fun create(): SearchProvider = SearchProviderDefault()
    }
}

class SearchProviderDefault : SearchProvider {
    override val searchApi: SearchApi
        get() = ApiServiceFactory<SearchApi>().create(
            baseURL = DEFAULT_BASE_URL,
            kClass = SearchApi::class.java
        )
    override val remoteDataSource: SearchDatasource
        get() = RemoteDataSource(searchApi)
    override val repository: SearchRepository
        get() = DefaultSearchRepository(remoteDataSource)
    override val useCase: SearchUseCase
        get() = SearchUseCase(repository)
}