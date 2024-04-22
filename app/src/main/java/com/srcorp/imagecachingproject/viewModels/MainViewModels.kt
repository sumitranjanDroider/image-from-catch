package com.srcorp.imagecachingproject.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srcorp.imagecachingproject.client.MainRepository
import com.srcorp.imagecachingproject.dataModel.BitMapData
import com.srcorp.imagecachingproject.dataModel.ImageData
import com.srcorp.imagecachingproject.dataModel.Thumbnail
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainViewModels constructor(private val mainRepository: MainRepository) :ViewModel(){

     var imageList= MutableLiveData<List<BitMapData>>()
     var errorMessage= MutableLiveData<String>()
    var errorCode= MutableLiveData<Int>()
    private var job: Job?=null
     val loading= MutableLiveData<Boolean>()
    private val exceptionHandler= CoroutineExceptionHandler{ _, throwable->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private fun onError(message:String){
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    init {
        loadImagesData()
    }
    fun refreshBtnClicked(){
        loadImagesData()
    }
    private fun loadImagesData(){
        job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            loading.postValue(true)
            val response=mainRepository.getImageLists()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val res=response.body()
                    filterImageResource(res)
                    loading.value=false
                }else{
                    onError("Error : ${response.message()}")
                }
            }


        }
    }

    private fun filterImageResource(res: List<ImageData>?) {
        val list= mutableListOf<BitMapData>()
        for (i in res!!.indices){
            val data=res[i].thumbnail!!
            val url="${data.domain}/${data.basePath}/0/${data.key}"
            list.add(BitMapData(url,"${i}_${data.key}"))
        }
       imageList.postValue(list)
    }
 }