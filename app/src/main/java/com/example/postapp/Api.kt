package com.example.postapp

import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("posts/rifqi")
    fun getPosts(): Call<ArrayList<PostResponse>>

}