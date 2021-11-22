package com.example.reddit.favorite.data.local.source

import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable

interface FavouriteLocalDataSource {
    fun addFavourite(postData: PostData) : Completable
}