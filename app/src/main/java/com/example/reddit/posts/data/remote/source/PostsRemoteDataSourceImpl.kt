package com.example.reddit.posts.data.remote.source

import com.example.reddit.posts.data.remote.api.RedditApi
import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single
import javax.inject.Inject

class PostsRemoteDataSourceImpl @Inject constructor(
    private val redditApi: RedditApi
) : PostsRemoteDataSource {

    override fun getPosts(query: String, limit: Int, after: String?): Single<PostsResponse> =
        if (query.isEmpty())
            redditApi.getPosts(limit, after)
        else
            redditApi.search(query, limit, after)
}