package com.avfeaster.androidlearnings.lesson1.imageLoader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.http.UrlRequest
import android.util.Log.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request

object NetworkClient {
    val client = OkHttpClient()

    suspend fun fetchImage(url: String): Bitmap? = withContext(Dispatchers.IO) {

        try {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body ?: return@use null
                body.byteStream().use { stream ->
                    BitmapFactory.decodeStream(stream)
                }
            }
        } catch (e: Exception) {
             null
         }
    }
}


/*
object NetworkClient {

    private val client = OkHttpClient()

    suspend fun fetch(url: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            val stream = response.body?.byteStream()
            BitmapFactory.decodeStream(stream)

        } catch (e: Exception) {
            null
        }
    }
}
 */