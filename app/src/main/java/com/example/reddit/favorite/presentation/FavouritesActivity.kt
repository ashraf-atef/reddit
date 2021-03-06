package com.example.reddit.favorite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.reddit.R
import com.example.common.common.presentation.Loading
import com.example.common.common.presentation.Success
import com.example.reddit.posts.data.model.PostData
import com.example.common.common.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_favourites.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesActivity : com.example.common.common.presentation.activity.BaseActivity(), FavouritesListener {

    private val favouritesAdapter = FavouritesAdapter(this)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var favouritesViewModel: FavouritesViewModel

    override fun getContentResource(): Int = R.layout.activity_favourites

    override fun init(state: Bundle?) {
        initRecyclerView()
        initViewModel()
        initToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favourites_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> {
               favouritesViewModel.deleteAllFavourites()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initRecyclerView() {
        rv_favourites.adapter = favouritesAdapter
    }

    private fun initViewModel() {
        favouritesViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(FavouritesViewModel::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favouritesViewModel.stateFlow.collect {
                    renderState(it)
                }
            }
        }
    }

    private fun initToolbar() {
        supportActionBar?.let {
            it.title = getString(R.string.title_favourites)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    private fun renderState(state: FavouritesState) {
        with(state) {
            // display loading from scratch
            pb_load_from_scratch.isVisible = favourites is com.example.common.common.presentation.Loading
            tv_no_data.isVisible =  favourites is com.example.common.common.presentation.Success && (favourites)().isEmpty()

            when (favourites) {
                is com.example.common.common.presentation.Success -> favouritesAdapter.addData((favourites)())
                // TODO: handle error case
            }
        }
    }

    override fun deleteFromFavourite(index: Int, post: PostData) {
        favouritesViewModel.deleteFavourite(post)
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, FavouritesActivity::class.java)
            context.startActivity(starter)
        }
    }
}