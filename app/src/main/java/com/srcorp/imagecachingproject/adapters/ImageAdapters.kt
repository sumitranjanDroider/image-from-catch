package com.srcorp.imagecachingproject.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.srcorp.imagecachingproject.R
import com.srcorp.imagecachingproject.client.Constant.discCatch
import com.srcorp.imagecachingproject.client.localLog
import com.srcorp.imagecachingproject.client.localMessages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@BindingAdapter("image_source","key")
fun showImageFromBitMap(imageView: ImageView,url:String,key:String){
   CoroutineScope(Dispatchers.Main).launch {
       val loading =
           BitmapFactory.decodeResource(imageView.resources, R.drawable.loading)
       imageView.setImageBitmap(loading)
       val bitmap:Bitmap? =discCatch.getBitmapFromDiskCache(imageView.context,key)
       if (bitmap!=null){
           localLog("showing image from disc catch")
           imageView.setImageBitmap(bitmap)
       }else{
           localLog("showing image from memory catch")
           val loadedBitmap= getBitmap(url,imageView.context)
           imageView.setImageBitmap(loadedBitmap)
           discCatch.saveBitmapToDiskCache(imageView.context,loadedBitmap!!,key)
       }
   }
}
suspend fun getBitmap(urls:String,context: Context):Bitmap? = withContext(Dispatchers.IO){
    return@withContext try {
        val url = URL(urls)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input: InputStream = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        localMessages(e.message!!,context)
        localLog(e.message!!)
        val failedBitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.failed)
        failedBitmap
    }
}