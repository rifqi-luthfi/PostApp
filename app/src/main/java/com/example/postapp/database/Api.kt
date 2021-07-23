package com.example.postapp.database

import retrofit2.Call
import retrofit2.http.*


interface Api {
    @GET("posts/rifqi")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @POST("post")
    fun insert(@Body post: CreatePostResponse): Call<PostResponse>

    @PUT(  "post/rifqi")
    fun update(@Body post: CreatePostResponse): Call<PostResponse>

    @GET("posts/rifqi")
    fun getById(@Query("id") id:String): Call<ArrayList<PostResponse>>

}