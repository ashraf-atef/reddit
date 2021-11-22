package com.example.reddit.favorite.data.local

import androidx.room.*
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : PostData): Completable

    @Query("SELECT * FROM post")
    fun fetchFavourites() : Observable<List<PostData>>

    @Delete
    fun deleteFavourite(posts: PostData): Completable

    @Query("Delete from post")
    fun deleteAll(): Completable
}