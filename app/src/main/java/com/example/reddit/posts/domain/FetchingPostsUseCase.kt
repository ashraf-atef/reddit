package com.example.reddit.posts.domain

import com.example.reddit.posts.data.model.PostsResponse
import io.reactivex.Single
import javax.inject.Inject

class FetchingPostsUseCase @Inject constructor(
    private val postsRepo: PostsRepo
) {
    operator fun invoke (limit: Int, after: String?): Single<PostsResponse> =
        postsRepo.getPosts(limit, after)
}