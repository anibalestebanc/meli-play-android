package com.example.search.data.remote

import com.example.network.ErrorHandler.suspendRunCatching
import com.example.search.data.remote.model.RemoteItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteDataSource(
    private val searchApi: SearchApi,
    private val siteId: String,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getItemsByText(value: String): Result<List<RemoteItem>> =
        withContext(ioDispatcher) {
            suspendRunCatching { searchApi.getSearchByText(siteId, value).result }
        }
}