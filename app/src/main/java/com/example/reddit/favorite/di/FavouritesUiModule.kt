package com.example.reddit.favorite.di

import androidx.lifecycle.ViewModel
import com.example.common.common.presentation.viewmodel.ViewModelKey
import com.example.reddit.favorite.presentation.FavouritesActivity
import com.example.reddit.favorite.presentation.FavouritesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavouritesUiModule {
    @Binds
    @IntoMap
    @com.example.common.common.presentation.viewmodel.ViewModelKey(FavouritesViewModel::class)
    abstract fun bindFavouritesViewModel(favouritesViewModel: FavouritesViewModel): ViewModel

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFavouritesActivity(): FavouritesActivity
}