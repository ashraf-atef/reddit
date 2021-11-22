package com.example.reddit.favorite.data

import com.example.reddit.favorite.data.local.source.FavouriteLocalDataSource
import com.example.reddit.favorite.domain.repo.FavouriteRepo
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import javax.inject.Inject

class FavouriteRepoImpl @Inject constructor(
    private val favouriteLocalDataSource: FavouriteLocalDataSource
) : FavouriteRepo{
    override fun addFavourite(postData: PostData): Completable =
        favouriteLocalDataSource.addFavourite(postData)
}