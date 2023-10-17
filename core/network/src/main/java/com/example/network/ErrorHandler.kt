package com.example.network

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

object ErrorHandler {
    fun handleError(e: Throwable): ApiError = when (e) {
        is IOException -> ApiError.Network
        is HttpException -> ApiError.Server
        else -> ApiError.Unknown
    }

    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
        Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}