package com.example.reddit.favorite.presentation

import android.annotation.SuppressLint
import com.example.reddit.favorite.domain.DeletingAllFavourite
import com.example.reddit.favorite.domain.DeletingFavourite
import com.example.reddit.favorite.domain.FetchingFavourites
import com.example.reddit.posts.data.model.PostData
import com.example.common.common.presentation.schedulers.SchedulersProvider
import com.example.common.common.presentation.viewmodel.BaseViewModel
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

    fun deleteFavourite(postData: PostData) {
        deletingFavourite(postData)
            .fireAndForget()
    }

    fun deleteAllFavourites() {
        deletingAllFavourite()
            .fireAndForget()
    }
}