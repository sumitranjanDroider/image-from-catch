package com.srcorp.imagecachingproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srcorp.imagecachingproject.client.MainRepository

class MainViewModelFactory constructor(private val mainRepository: MainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModels::class.java)){
            MainViewModels(mainRepository) as T
        }else{
            throw IllegalArgumentException("MainViewModel needed but got different")
        }
    }
}