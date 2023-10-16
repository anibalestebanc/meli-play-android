package com.example.network

sealed class ApiError {
    data object Network : ApiError()
    data object Server : ApiError()
    data object Unknown : ApiError()
}
