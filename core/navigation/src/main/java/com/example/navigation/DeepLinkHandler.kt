package com.example.navigation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle

object DeepLinkHandler {
    fun openDeepLink(context: Context, deepLink: String, bundle: Bundle? = null) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(deepLink)
        }
        val resolveInfo = intent.resolveActivity(context.packageManager)

        //Deprecate resolve activity
        //context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        bundle?.let {
            intent.putExtras(it)
        }
        resolveInfo?.let {
            context.startActivity(intent)
        }
    }
}