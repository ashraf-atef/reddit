package com.example.reddit.favorite.data.local.source

import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable

interface FavouriteLocalDataSource {
    fun addFavourite(postData: PostData) : Completable
    fun fetchFavourites(): Observable<List<PostData>>
    fun deleteFavourite(postData: PostData): Completable
    fun deleteAllFavourites(): Completable
}