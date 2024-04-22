package com.srcorp.imagecachingproject.client

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.srcorp.imagecachingproject.client.Constant.TAG_MAIN

fun localMessages(msg:String,context: Context){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}
fun localLog(msg: String){
    Log.d(TAG_MAIN,msg)
}