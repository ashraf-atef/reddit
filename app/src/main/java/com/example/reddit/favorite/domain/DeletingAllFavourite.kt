package com.example.reddit.favorite.domain

import com.example.reddit.favorite.domain.repo.FavouriteRepo
import io.reactivex.Completable
import javax.inject.Inject

class DeletingAllFavourite @Inject constructor(
    private val favouriteRepo: FavouriteRepo
) {
    operator fun invoke(): Completable =
        favouriteRepo.deleteAllFavourites()
}