package com.avfeaster.androidlearnings.lesson1.imageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

object DiskCache {

    private  fun fileName(url: String) = url.hashCode().toString()

    private fun getFile(context: Context, url: String): File {
        return File(context.cacheDir, fileName(url))
    }

    fun get(context: Context, url: String): Bitmap? {
        val file = getFile(context,url)
        if(file.exists()) {
            return  BitmapFactory.decodeFile(file.absolutePath)
        } else {
            return null
        }
    }




    fun put(context: Context, url: String, bitMap: Bitmap) {
        val file = getFile(context, url)
        FileOutputStream(file).use { it ->
            bitMap.compress(Bitmap.CompressFormat.PNG,100,it)
        }
    }


}

