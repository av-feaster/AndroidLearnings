package com.avfeaster.androidlearnings.lesson1.imageLoader

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ImageLoader {

    suspend fun load(context: Context, url: String): Bitmap? {
        if (url.isEmpty()) {
            return null
        }

        MemoryCache.get(url)?.let { return it }

        return withContext(Dispatchers.IO) {
            DiskCache.get(context, url)?.let { fromDisk ->
                MemoryCache.put(url, fromDisk)
                return@withContext fromDisk
            }

            val bitmap = NetworkClient.fetchImage(url)
            bitmap?.let {
                MemoryCache.put(url, it)
                DiskCache.put(context, url, it)
                return@withContext it
            }
            null
        }
    }
}

suspend fun ImageView.loadUrl(url: String) {
    withContext(Dispatchers.Main) {
        tag = url
    }
    val bitmap = ImageLoader.load(context, url)
    withContext(Dispatchers.Main) {
        if (tag == url) {
            setImageBitmap(bitmap)
        }
    }
}


