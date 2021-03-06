package com.example.postapp.database

import com.example.postapp.PostAppInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(PostAppInterceptor())
    }.build()
    private const val BASE_URL = "http://post.jonathandarwin.com/"

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)

    }
}