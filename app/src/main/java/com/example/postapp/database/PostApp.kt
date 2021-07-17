package com.example.postapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostApp(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var title: String,
    var description: String

)