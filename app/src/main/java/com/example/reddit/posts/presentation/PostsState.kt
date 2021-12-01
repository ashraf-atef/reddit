package com.example.reddit.posts.presentation

import com.example.common.common.presentation.Async
import com.example.common.common.presentation.Uninitialized
import com.example.common.common.presentation.viewmodel.BaseState
import com.example.reddit.posts.data.model.PostsResponseData

data class PostsState(
        val searchText: String = "",
        val posts: com.example.common.common.presentation.Async<PostsResponseData> = com.example.common.common.presentation.Uninitialized
): com.example.common.common.presentation.viewmodel.BaseState