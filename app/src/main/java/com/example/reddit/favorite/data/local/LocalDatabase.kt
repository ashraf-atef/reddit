package com.example.reddit.favorite.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reddit.posts.data.model.PostData

@Database(entities = [PostData::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun postDao(): PostsDao

    companion object {

        lateinit var instance: LocalDatabase
        fun getInstance(context: Context): LocalDatabase {
            synchronized(LocalDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java, "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                return instance
            }
        }
    }
}