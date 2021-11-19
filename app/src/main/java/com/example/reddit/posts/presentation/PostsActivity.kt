package com.example.reddit.posts.presentation

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.reddit.R
import com.example.reddit.common.presentation.EndlessRecyclerViewOnScrollListener
import com.example.reddit.common.presentation.Loading
import com.example.reddit.common.presentation.Success
import com.example.restaurant.common.presentationLayer.BaseActivity
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var postsViewModel: PostsViewModel
    private val postAdapter = PostsAdapter()
    lateinit var endlessRecyclerViewOnScrollListener: EndlessRecyclerViewOnScrollListener

    override fun getContentResource(): Int = R.layout.activity_posts

    override fun init(state: Bundle?) {
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        rv_posts.adapter = postAdapter

        endlessRecyclerViewOnScrollListener = object : EndlessRecyclerViewOnScrollListener() {

            override fun onLoadMore() {
                postsViewModel.fetchPosts()
            }
        }
        rv_posts.addOnScrollListener(endlessRecyclerViewOnScrollListener)
    }

    private fun initViewModel() {
        postsViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(PostsViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postsViewModel.stateFlow.collect {
                    renderState(it)
                }
            }
        }
    }

    private fun renderState(state: PostsState) {
        with(state) {
            val isPostsEmpty = posts()?.children?.isNullOrEmpty() ?: true
            // display loading from scratch
            pb_load_from_scratch.isVisible = state.posts is Loading && isPostsEmpty
            // display loading more
            pb_load_more.isVisible = state.posts is Loading && !isPostsEmpty

            // display data on success
            if (posts is Success) {
                postAdapter.addData((posts)().children)
            }
        }
    }
}