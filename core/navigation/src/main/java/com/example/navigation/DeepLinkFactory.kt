package com.example.navigation

import android.net.Uri

object DeepLinkFactory {

    private const val DEFAULT_APP_SCHEMA = "meliplay://"
    fun create(host: String, params: Map<String, String>): String{
        val uri = Uri.parse("${DEFAULT_APP_SCHEMA}${host}").buildUpon()
        params.forEach { param ->
            uri.appendQueryParameter(param.key, param.value)
        }
        return uri.build().toString()
    }
}