package com.srcorp.imagecachingproject.client

import com.srcorp.imagecachingproject.client.Constant.BASE_URL
import com.srcorp.imagecachingproject.dataModel.ImageData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("misc/media-coverages")
    suspend fun getImageLists(@Query("limit")limit:Int): Response<List<ImageData>>
    companion object {
        var retrofitSer: ApiService? = null
        fun getLocalInstance() : ApiService {
            if (retrofitSer == null) {
                val client: OkHttpClient = OkHttpClient().newBuilder()
                    .connectTimeout(400, TimeUnit.SECONDS)
                    .readTimeout(400, TimeUnit.SECONDS).build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitSer = retrofit.create(ApiService::class.java)
            }
            return retrofitSer!!
        }
    }
}