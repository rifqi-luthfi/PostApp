package com.example.postapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(
    entities = [PostApp::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun DaoPostApp(): DaoPostApp
    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Postapp.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance as AppDatabase
        }
    }
}