package com.srcorp.imagecachingproject.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.srcorp.imagecachingproject.R
import com.srcorp.imagecachingproject.adapters.MainImageListAdapter
import com.srcorp.imagecachingproject.client.ApiService
import com.srcorp.imagecachingproject.client.MainRepository
import com.srcorp.imagecachingproject.client.localLog
import com.srcorp.imagecachingproject.dataModel.BitMapData
import com.srcorp.imagecachingproject.dataModel.ImageData
import com.srcorp.imagecachingproject.databinding.ActivityMainBinding
import com.srcorp.imagecachingproject.viewModels.MainViewModelFactory
import com.srcorp.imagecachingproject.viewModels.MainViewModels

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModels:MainViewModels by viewModels<MainViewModels> {
        val service=ApiService.getLocalInstance()
        val repo=MainRepository(service)
        MainViewModelFactory(repo)
    }
    private lateinit var imageAdapter:MainImageListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            //Recycler related
            mainRecycler.layoutManager=GridLayoutManager(this@MainActivity,3)
            imageAdapter=MainImageListAdapter()
            mainRecycler.adapter=imageAdapter
            viewModels.loading.observe(this@MainActivity){

            }
            viewModels.errorMessage.observe(this@MainActivity){

            }
            viewModels.cache.observe(this@MainActivity){
              val list= mutableListOf<BitMapData>()
              for (i in it){
                   list.add(BitMapData(i.value,i.key))
              }
                imageAdapter.updateMainImageListAdapter(list)
                localLog(list.toString())
            }


        }
    }
}