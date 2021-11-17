package com.example.reddit.posts.presentation

import com.example.reddit.common.presentation.Async
import com.example.reddit.common.presentation.Uninitialized
import com.example.reddit.common.presentation.viewmodel.BaseState
import com.example.reddit.posts.data.model.Post
import com.example.reddit.posts.data.model.PostsResponseData

data class PostsState(
        val posts: Async<PostsResponseData> = Uninitialized
): BaseState