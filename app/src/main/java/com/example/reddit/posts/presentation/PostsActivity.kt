package com.example.reddit.posts.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.reddit.common.presentation.EndlessRecyclerViewOnScrollListener
import com.example.reddit.common.presentation.Loading
import com.example.reddit.common.presentation.Success
import com.example.reddit.common.presentation.Uninitialized
import com.example.reddit.posts.data.model.Post
import com.example.restaurant.common.presentationLayer.BaseActivity
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import com.example.reddit.R
import com.example.reddit.favorite.presentation.FavouritesActivity


class PostsActivity : BaseActivity(), PostsListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var postsViewModel: PostsViewModel
    private val postAdapter = PostsAdapter(this)
    lateinit var endlessRecyclerViewOnScrollListener: EndlessRecyclerViewOnScrollListener

    override fun getContentResource(): Int = R.layout.activity_posts

    override fun init(state: Bundle?) {
        initRecyclerView()
        initViewModel()
        initSearchEdittext()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.posts_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favourites -> {
                FavouritesActivity.start(this)
            }
        }
        return super.onOptionsItemSelected(item)
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

            // TODO: handle empty state
            when (posts) {
                is Success -> postAdapter.addData((posts)().children)
                is Uninitialized -> postAdapter.addData(listOf())
                // TODO: handle error case
            }
        }
    }

    private fun initSearchEdittext() {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                postsViewModel.searchPosts(s.toString())
            }
        })
    }

    override fun addToFavourite(index: Int, post: Post) {
        postsViewModel.addFavourite(post)
        Toast.makeText(
            baseContext,
            "Added to favourites successfully",
            Toast.LENGTH_LONG
        ).show()
    }
}