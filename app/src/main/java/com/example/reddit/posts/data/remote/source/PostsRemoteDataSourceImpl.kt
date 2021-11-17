package com.example.reddit.posts.data.remote.source

import com.example.reddit.posts.data.remote.api.RedditApi
import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single
import javax.inject.Inject

class PostsRemoteDataSourceImpl @Inject constructor(
    private val redditApi: RedditApi
) : PostsRemoteDataSource {

    override fun getPosts(limit: Int, after: String?): Single<PostsResponse> =
        redditApi.getPosts(limit, after)
}