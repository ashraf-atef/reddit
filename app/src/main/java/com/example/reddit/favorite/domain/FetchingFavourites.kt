package com.example.reddit.favorite.domain

import com.example.reddit.favorite.domain.repo.FavouriteRepo
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Observable
import javax.inject.Inject

class FetchingFavourites @Inject constructor(
    private val favouriteRepo: FavouriteRepo
) {
    operator fun invoke() : Observable<List<PostData>> = favouriteRepo.fetchFavourites()
}