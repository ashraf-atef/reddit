package com.example.reddit.posts.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "post")
data class PostData(
        @PrimaryKey
        val id: String,
        val title: String,
        val thumbnail: String,
        val isVideo: Boolean
)