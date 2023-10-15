package com.example.search.domain.repository

import com.example.search.domain.model.Item

interface SearchRepository {
    suspend fun getItemsByText(value : String) : List<Item>
}