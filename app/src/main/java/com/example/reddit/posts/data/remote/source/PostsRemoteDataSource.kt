package com.example.reddit.posts.data.remote.source

import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single
import retrofit2.http.Query

interface PostsRemoteDataSource {
    fun getPosts(
      limit: Int,
       after: String?
    ): Single<PostsResponse>
}