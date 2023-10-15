package com.example.detail.data.remote

import com.example.detail.data.remote.model.RemoteItem
import com.example.detail.data.source.DetailDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val api: ItemsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DetailDataSource {
    override suspend fun getItemById(id: String) : RemoteItem =
        withContext(dispatcher){
            api.getItemById(id)[0].body
        }
}