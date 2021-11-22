package com.example.reddit.favorite.presentation

import com.example.reddit.common.presentation.Async
import com.example.reddit.common.presentation.Uninitialized
import com.example.reddit.common.presentation.viewmodel.BaseState
import com.example.reddit.posts.data.model.PostData

data class FavouritesState (
    val favourites: Async<List<PostData>> = Uninitialized
): BaseState