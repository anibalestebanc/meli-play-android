package com.example.search.data.source

import com.example.search.data.remote.model.RemoteItem

interface SearchDatasource {
    suspend fun getItemsByText(value : String) : List<RemoteItem>
}