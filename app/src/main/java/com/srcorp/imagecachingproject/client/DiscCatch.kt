package com.srcorp.imagecachingproject.client

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class DiscCatch{
    //Disc Caching
    suspend fun getBitmapFromDiskCache(context: Context, key: String): Bitmap? = withContext(Dispatchers.IO) {
        val cacheDir = context.cacheDir
        val file = File(cacheDir, key)
        if (file.exists()) {
            return@withContext BitmapFactory.decodeFile(file.absolutePath)
        }
        return@withContext null
    }
    suspend fun saveBitmapToDiskCache(context: Context, bitmap: Bitmap, key: String) = withContext(
        Dispatchers.IO) {
        val cacheDir = context.cacheDir
        val file = File(cacheDir, key)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
        }
    }

    fun clearDiskCache(context: Context) {
        val cacheDir = context.cacheDir
        cacheDir.deleteRecursively()
    }
}