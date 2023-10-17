package com.example.network

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MockWebServerFactory<T> {
    private val MOCK_TIME_OUT = 5L
    fun create(mockWebServer: MockWebServer, kClass: Class<T>): T {
        return getRetrofitClient(mockWebServer).create(kClass)
    }

    private fun getRetrofitClient(mockWebServer: MockWebServer): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(createOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val createOkHttpClient = OkHttpClient.Builder()
        .connectTimeout(MOCK_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(MOCK_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(MOCK_TIME_OUT, TimeUnit.SECONDS)
        .build()
}