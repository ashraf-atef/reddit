package com.example.reddit.posts.presentation

import android.annotation.SuppressLint
import com.example.reddit.common.presentation.Success
import com.example.reddit.common.presentation.Uninitialized
import com.example.reddit.common.presentation.viewmodel.BaseViewModel
import com.example.reddit.favorite.domain.AddingFavourite
import com.example.reddit.posts.data.model.Post
import com.example.reddit.posts.domain.FetchingPostsUseCase
import com.example.restaurant.common.presentationLayer.rx.schedulers.SchedulersProvider
import javax.inject.Inject

const val POSTS_LIMIT = 10

class PostsViewModel @Inject constructor(
    private val fetchingPostsUseCase: FetchingPostsUseCase,
    private val addingFavourite: AddingFavourite,
    schedulersProvider: SchedulersProvider
) : BaseViewModel<PostsState>(PostsState(), schedulersProvider) {

    init {
        fetchPosts()
    }

    fun searchPosts(query: String) {
        setState(
            getState().copy(
                searchText = query,
                posts = Uninitialized
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

    @SuppressLint("CheckResult")
    fun addFavourite(post: Post) {
        addingFavourite(post.data)
            .subscribeOn(schedulersProvider.io())
            .blockingGet()
    }
}
