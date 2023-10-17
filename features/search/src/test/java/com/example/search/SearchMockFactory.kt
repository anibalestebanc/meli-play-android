package com.example.search

import com.example.search.data.remote.model.RemoteItem
import com.example.search.data.remote.model.SearchResponse
import com.example.search.domain.model.Item

fun createSearchResponse(): SearchResponse = SearchResponse(
    result = listOf(createRemoteSearchItem())
)

fun createRemoteSearchItem() = RemoteItem(
    id = "1",
    title = "Title",
    price = 100,
    thumbnail = "image.jpg",
    originalPrice = 9999,
    officialStoreName = "Apple",
    categoryId = "123",
    availableQuantity = 10
)

fun createDomainItem() = Item(
    id = "1",
    title = "Title",
    price = 100,
    thumbnail = "image.jpg",
    originalPrice = 9999,
    officialStoreName = "Apple",
    categoryId = "123",
    availableQuantity = 10
)