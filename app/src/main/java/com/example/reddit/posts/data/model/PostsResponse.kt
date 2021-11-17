package com.example.reddit.posts.data.model

import androidx.annotation.Keep

@Keep
data class PostsResponse(
        val data: PostsResponseData
)

@Keep
data class PostsResponseData(
        val after: String,
        val children: List<Post>
)

@Keep
data class Post(
        val data: PostData
)

@Keep
data class PostData(
        val id: String,
        val title: String,
        val thumbnail: String,
        val isVideo: Boolean
)