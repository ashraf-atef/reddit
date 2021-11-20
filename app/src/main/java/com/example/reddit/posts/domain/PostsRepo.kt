package com.example.reddit.posts.domain

import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single

interface PostsRepo {
    fun getPosts(
        query: String,
        limit: Int,
        after: String?
    ): Single<PostsResponse>
}