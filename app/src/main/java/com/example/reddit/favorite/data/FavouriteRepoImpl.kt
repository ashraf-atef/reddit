package com.example.reddit.favorite.data

import com.example.reddit.favorite.data.local.source.FavouriteLocalDataSource
import com.example.reddit.favorite.domain.repo.FavouriteRepo
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavouriteRepoImpl @Inject constructor(
    private val favouriteLocalDataSource: FavouriteLocalDataSource
) : FavouriteRepo{
    override fun addFavourite(postData: PostData): Completable =
        favouriteLocalDataSource.addFavourite(postData)

    override fun fetchFavourites(): Observable<List<PostData>> =
        favouriteLocalDataSource.fetchFavourites()

    override fun deleteFavourite(postData: PostData) =
        favouriteLocalDataSource.deleteFavourite(postData)

    override fun deleteAllFavourites(): Completable = favouriteLocalDataSource.deleteAllFavourites()
}