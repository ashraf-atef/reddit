package com.example.reddit.favorite.di

import androidx.lifecycle.ViewModel
import com.example.reddit.common.presentation.viewmodel.ViewModelKey
import com.example.reddit.favorite.presentation.FavouritesActivity
import com.example.reddit.favorite.presentation.FavouritesViewModel
import com.example.reddit.posts.presentation.PostsActivity
import com.example.reddit.posts.presentation.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavouritesUiModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    abstract fun bindFavouritesViewModel(favouritesViewModel: FavouritesViewModel): ViewModel

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFavouritesActivity(): FavouritesActivity
}