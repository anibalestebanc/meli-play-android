package com.example.search.data.mappers

import com.example.search.data.remote.model.RemoteItem
import com.example.search.domain.model.Item


fun RemoteItem.asDomain() = Item(
    id = id
)