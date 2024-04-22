package com.srcorp.imagecachingproject.client

class MainRepository constructor(private val service: ApiService) {
    suspend fun getImageLists()=service.getImageLists(100)
}