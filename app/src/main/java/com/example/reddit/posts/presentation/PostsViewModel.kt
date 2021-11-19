package com.example.reddit.posts.presentation

import com.example.reddit.common.presentation.Success
import com.example.reddit.common.presentation.viewmodel.BaseViewModel
import com.example.reddit.posts.domain.FetchingPostsUseCase
import com.example.restaurant.common.presentationLayer.rx.schedulers.SchedulersProvider
import javax.inject.Inject

const val POSTS_LIMIT = 10

class PostsViewModel @Inject constructor(
        private val fetchingPostsUseCase: FetchingPostsUseCase,
        schedulersProvider: SchedulersProvider
) : BaseViewModel<PostsState>(PostsState(), schedulersProvider) {

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        fetchingPostsUseCase(
                POSTS_LIMIT,
                getState().posts()?.after
        )
                .map { it.data }
                .execute (retainValue = getState().posts) { postsResponse ->
                    if (postsResponse is Success) {
                        val oldList = getState().posts()?.children ?: listOf()
                        val newList = (postsResponse as Success)().children
                        copy(
                                posts = Success(
                                        (postsResponse as Success)().copy(
                                               children = oldList + newList
                                        )
                                )
                        )
                    } else {
                        copy(
                                posts = postsResponse
                        )
                    }
                }
    }
}
