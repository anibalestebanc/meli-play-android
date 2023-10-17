package com.example.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DEFAULT_BASE_URL = "https://api.mercadolibre.com/"

const val DEFAULT_TIME_OUT = 15L

class ApiServiceFactory<T> {
    fun create(
        baseURL: String,
        timeOut: Long = DEFAULT_TIME_OUT,
        kClass: Class<T>
    ): T {
        val okHttpClient = createOkHttpClient(timeOut)
        return createRetrofit(baseURL, okHttpClient).create(kClass)
    }

    private fun createRetrofit(baseURL: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun createOkHttpClient(timeOut: Long): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        /*   val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
           else HttpLoggingInterceptor.Level.NONE*/
        val level = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().apply {
            setLevel(level = level)
        }
    }

}