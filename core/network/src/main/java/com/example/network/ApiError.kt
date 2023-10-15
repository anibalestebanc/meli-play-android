package com.example.network

sealed class ApiError {
    data class Network(var message: String) : ApiError()
    data class Server(var message: String) : ApiError()
    data class Unknown(var message: String) : ApiError()
}
