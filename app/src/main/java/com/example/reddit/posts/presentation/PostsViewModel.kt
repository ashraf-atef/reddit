package com.example.reddit.posts.presentation

import android.annotation.SuppressLint
import com.example.reddit.favorite.domain.AddingFavourite
import com.example.reddit.posts.data.model.Post
import com.example.reddit.posts.domain.FetchingPostsUseCase
import com.example.common.common.presentation.schedulers.SchedulersProvider
import javax.inject.Inject

const val POSTS_LIMIT = 10

class PostsViewModel @Inject constructor(
    private val fetchingPostsUseCase: FetchingPostsUseCase,
    private val addingFavourite: AddingFavourite,
    schedulersProvider: SchedulersProvider
) : com.example.common.common.presentation.viewmodel.BaseViewModel<PostsState>(PostsState(), schedulersProvider) {

    init {
        fetchPosts()
    }

    fun searchPosts(query: String) {
        setState(
            getState().copy(
                searchText = query,
                posts = com.example.common.common.presentation.Uninitialized
            )
        )
        fetchPosts()
    }

    fun fetchPosts() {
        fetchingPostsUseCase(
            getState().searchText,
            POSTS_LIMIT,
            getState().posts()?.after
        )
            .map { it.data }
            .execute(retainValue = getState().posts) { postsResponse ->
                if (postsResponse is com.example.common.common.presentation.Success) {
                    val oldList = getState().posts()?.children ?: listOf()
                    val newList = (postsResponse as com.example.common.common.presentation.Success)().children
                    copy(
                        posts = com.example.common.common.presentation.Success(
                            (postsResponse as com.example.common.common.presentation.Success)().copy(
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

    @SuppressLint("CheckResult")
    fun addFavourite(post: Post) {
        addingFavourite(post.data)
            .subscribeOn(schedulersProvider.io())
            .blockingGet()
    }
}
