package com.example.search.presentation.di

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

    val baseURL: String

    val disPatcher: CoroutineDispatcher

    val searchApi: SearchApi

    val remoteDataSource: SearchDatasource

    val repository: SearchRepository

    val useCase: SearchUseCase

    companion object {
        fun create(): SearchProvider = SearchProviderDefault()
    }
}

class SearchProviderDefault : SearchProvider {
    override val baseURL: String
        get() = "https://api.mercadolibre.com/"
    override val disPatcher: CoroutineDispatcher
        get() = Dispatchers.IO
    override val searchApi: SearchApi
        get() = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    override val remoteDataSource: SearchDatasource
        get() = RemoteDataSource(searchApi, disPatcher)
    override val repository: SearchRepository
        get() = DefaultSearchRepository(remoteDataSource)
    override val useCase: SearchUseCase
        get() = SearchUseCase(repository)
}