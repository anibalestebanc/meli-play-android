package com.example.search.data.remote

import com.example.search.data.remote.model.RemoteItem
import com.example.search.data.source.SearchDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val searchApi: SearchApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchDatasource {
    override suspend fun getItemsByText(value: String): List<RemoteItem> =
        withContext(ioDispatcher) {
            searchApi.getSearchByText("MLC", value).result
        }
}