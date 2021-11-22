package com.example.reddit.favorite.domain

import com.example.reddit.favorite.domain.repo.FavouriteRepo
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import javax.inject.Inject

class DeletingFavourite @Inject constructor(
    private val favouriteRepo: FavouriteRepo
) {
    operator fun invoke(postData: PostData): Completable =
        favouriteRepo.deleteFavourite(postData)
}