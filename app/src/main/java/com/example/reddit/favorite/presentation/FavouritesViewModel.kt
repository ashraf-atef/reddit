package com.example.reddit.favorite.presentation

import android.annotation.SuppressLint
import com.example.reddit.common.presentation.viewmodel.BaseViewModel
import com.example.reddit.favorite.domain.AddingFavourite
import com.example.reddit.favorite.domain.DeletingAllFavourite
import com.example.reddit.favorite.domain.DeletingFavourite
import com.example.reddit.favorite.domain.FetchingFavourites
import com.example.reddit.posts.data.model.PostData
import com.example.reddit.posts.domain.FetchingPostsUseCase
import com.example.restaurant.common.presentationLayer.rx.schedulers.SchedulersProvider
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val fetchingFavourites: FetchingFavourites,
    private val deletingFavourite: DeletingFavourite,
    private val deletingAllFavourite: DeletingAllFavourite,
    schedulersProvider: SchedulersProvider
): BaseViewModel<FavouritesState>(FavouritesState(), schedulersProvider) {

    init {
        fetchFavourites()
    }

    private fun fetchFavourites() {
        fetchingFavourites()
            .execute {
                copy(
                    favourites = it
                )
            }
    }

    @SuppressLint("CheckResult")
    fun deleteFavourite(postData: PostData) {
        deletingFavourite(postData)
            .subscribeOn(schedulersProvider.io())
            .blockingGet()
    }

    @SuppressLint("CheckResult")
    fun deleteAllFavourites() {
        deletingAllFavourite()
            .subscribeOn(schedulersProvider.io())
            .blockingGet()
    }
}