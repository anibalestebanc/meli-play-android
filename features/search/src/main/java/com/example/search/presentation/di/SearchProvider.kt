package com.example.search.presentation.di

import com.example.network.ApiServiceFactory
import com.example.network.DEFAULT_BASE_URL
import com.example.search.data.DefaultSearchRepository
import com.example.search.data.remote.SearchRemoteDataSource
import com.example.search.data.remote.SearchApi
import com.example.search.domain.repository.SearchRepository
import com.example.search.domain.usecase.SearchUseCase

interface SearchProvider {

    val siteId: String

    val searchApi: SearchApi

    val remoteDataSource: SearchRemoteDataSource

    val repository: SearchRepository

    val useCase: SearchUseCase

    companion object {
        fun create(): SearchProvider = SearchProviderDefault()
    }
}

class SearchProviderDefault : SearchProvider {
    override val siteId: String
        get() = "MLC"
    override val searchApi: SearchApi
        get() = ApiServiceFactory<SearchApi>().create(
            baseURL = DEFAULT_BASE_URL,
            kClass = SearchApi::class.java
        )
    override val remoteDataSource: SearchRemoteDataSource
        get() = SearchRemoteDataSource(searchApi, siteId)
    override val repository: SearchRepository
        get() = DefaultSearchRepository(remoteDataSource)
    override val useCase: SearchUseCase
        get() = SearchUseCase(repository)
}