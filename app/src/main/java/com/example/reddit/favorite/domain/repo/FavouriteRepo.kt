package com.example.reddit.favorite.domain.repo

import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable

interface FavouriteRepo {
    fun addFavourite(postData: PostData) : Completable
}