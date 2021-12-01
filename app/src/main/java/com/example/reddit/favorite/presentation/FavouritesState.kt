package com.example.reddit.favorite.presentation

import com.example.common.common.presentation.Async
import com.example.common.common.presentation.Uninitialized
import com.example.common.common.presentation.viewmodel.BaseState
import com.example.reddit.posts.data.model.PostData

data class FavouritesState (
    val favourites: com.example.common.common.presentation.Async<List<PostData>> = com.example.common.common.presentation.Uninitialized
): com.example.common.common.presentation.viewmodel.BaseState