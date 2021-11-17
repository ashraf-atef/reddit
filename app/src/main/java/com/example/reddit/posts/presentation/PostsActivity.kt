package com.example.reddit.posts.presentation

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.reddit.R
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

    override fun getContentResource(): Int = R.layout.activity_posts

    override fun init(state: Bundle?) {
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        rv_posts.adapter = postAdapter
    }

    private fun initViewModel() {
        postsViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(PostsViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postsViewModel.stateFlow.collect {
                    if (it.posts is Success) {
                        postAdapter.addData((it.posts)().children)
                    }
                }
            }
        }
    }
}