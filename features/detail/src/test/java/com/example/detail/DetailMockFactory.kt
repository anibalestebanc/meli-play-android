package com.example.detail

import com.example.detail.data.remote.model.RemoteItem
import com.example.detail.data.remote.model.ResponseDetail
import com.example.detail.domain.model.ItemDetail

fun createItemResponse(): List<ResponseDetail> = listOf(
    createResponseDetail()
)

fun createResponseDetail() = ResponseDetail(
    body = createRemoteItem(),
    code = 200
)

fun createRemoteItem() = RemoteItem(
    id = "1",
    title = "Title",
    price = 100,
    thumbnail = "image.jpg",
    originalPrice = 9999,
    officialStoreName = "Apple",
    categoryId = "123",
    availableQuantity = 10,
    pictures = listOf()
)

fun createDomainItem() = ItemDetail(
    id = "1",
    title = "Title",
    price = 100,
    thumbnail = "image.jpg",
    originalPrice = 9999,
    officialStoreName = "Apple",
    categoryId = "123",
    availableQuantity = 10,
    pictures = listOf()
)

