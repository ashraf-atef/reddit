package com.example.reddit.favorite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : PostData): Completable

    @Query("SELECT * FROM post")
    fun getFavorites() : Observable<List<PostData>>
}