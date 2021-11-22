package com.example.reddit.posts.di

import androidx.lifecycle.ViewModel
import com.example.reddit.common.presentation.viewmodel.ViewModelKey
import com.example.reddit.posts.presentation.PostsActivity
import com.example.reddit.posts.presentation.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PostsUiModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel

    @ContributesAndroidInjector(modules = [])
    abstract fun bindPostActivity(): PostsActivity
}