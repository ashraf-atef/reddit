package com.example.reddit.favorite.domain.repo

import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable

interface FavouriteRepo {
    fun addFavourite(postData: PostData) : Completable
    fun fetchFavourites(): Observable<List<PostData>>
    fun deleteFavourite(postData: PostData): Completable
    fun deleteAllFavourites(): Completable
}