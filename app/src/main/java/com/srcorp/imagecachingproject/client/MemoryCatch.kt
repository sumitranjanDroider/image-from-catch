package com.srcorp.imagecachingproject.client

import android.graphics.Bitmap
import androidx.collection.LruCache
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoryCatch {
    private val cache: HashMap<String, Bitmap?> =HashMap()

    suspend fun addBitmapToMemoryCache(key:String,bitmap: Bitmap?) = withContext(Dispatchers.IO){
        if (getBitMapFromMemoryCache(key)==null){
           cache.put(key, bitmap!!)
        }
    }
    suspend fun getBitMapFromMemoryCache(key:String):Bitmap? = withContext(Dispatchers.IO){
        return@withContext cache[key]
    }
    fun clearMemoryCatch(){
        cache.clear()
    }
}
























