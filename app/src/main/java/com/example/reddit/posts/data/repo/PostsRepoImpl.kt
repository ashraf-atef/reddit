package com.example.reddit.posts.data.repo

import com.example.reddit.posts.data.model.PostsResponse
import com.example.reddit.posts.data.remote.source.PostsRemoteDataSource
import com.example.reddit.posts.domain.PostsRepo
import io.reactivex.Single
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val postsRemoteDataSource: PostsRemoteDataSource
) : PostsRepo{

    override fun getPosts(query: String, limit: Int, after: String?): Single<PostsResponse> =
        postsRemoteDataSource.getPosts(query, limit, after)

}