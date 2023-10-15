package com.example.detail.data.source

import com.example.detail.data.remote.model.RemoteItem

interface DetailDataSource {
    suspend fun getItemById(id: String): RemoteItem
}