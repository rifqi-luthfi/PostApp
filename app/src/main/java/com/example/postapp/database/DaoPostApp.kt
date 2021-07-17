package com.example.postapp.database

import androidx.room.*

@Dao
interface DaoPostApp {

    @Insert
    fun insert(postApp: PostApp): Long

    @Update
    fun update(postApp: PostApp): Int

    @Delete
    fun delete(postApp: PostApp): Int


    @Query("SELECT * FROM PostApp WHERE id = :id ")
    fun getById(id: Long): PostApp

    @Query("SELECT * FROM PostApp")
    fun get(): List<PostApp>
}
