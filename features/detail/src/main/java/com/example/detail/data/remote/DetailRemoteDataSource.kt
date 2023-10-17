package com.example.detail.data.remote

import com.example.detail.data.remote.model.RemoteItem
import com.example.network.ErrorHandler.suspendRunCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRemoteDataSource(
    private val itemsApi: ItemsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getItemById(id: String): Result<RemoteItem> =
        withContext(dispatcher) {
            suspendRunCatching { itemsApi.getItemById(id)[0].body }
        }
}