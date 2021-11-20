package com.example.reddit.posts.data.remote.api

import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("top.json")
    fun getPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String?
    ): Single<PostsResponse>

    @GET("search.json")
    fun search(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("after") after: String?
    ): Single<PostsResponse>
}