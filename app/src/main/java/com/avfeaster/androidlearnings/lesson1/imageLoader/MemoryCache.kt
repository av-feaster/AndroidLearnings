package com.avfeaster.androidlearnings.lesson1.imageLoader

import android.graphics.Bitmap
import android.util.LruCache

class BitmapMemCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize) {

    override fun sizeOf(key: String, value: Bitmap): Int {
        return value.byteCount / 1024
    }
}


object MemoryCache  {

    private val maxMemory = (Runtime.getRuntime().maxMemory() /1024).toInt()
    private val cacheSize = maxMemory/8

    val cache= BitmapMemCache(maxMemory/8)

    fun get(key: String) = cache.get(key)

    fun put(key: String, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }

}