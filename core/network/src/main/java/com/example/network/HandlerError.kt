package com.example.network

import retrofit2.HttpException
import java.io.IOException

class HandlerError {
    fun handle(e: Throwable): ApiError = when (e) {
        is IOException -> ApiError.Network("")
        is HttpException -> ApiError.Server("")
        else -> ApiError.Unknown("")
    }
}