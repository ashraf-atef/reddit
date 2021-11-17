package com.example.reddit.posts.di

import androidx.lifecycle.ViewModel
import com.example.reddit.common.presentation.viewmodel.ViewModelKey
import com.example.reddit.posts.data.remote.api.RedditApi
import com.example.reddit.posts.data.remote.source.PostsRemoteDataSource
import com.example.reddit.posts.data.remote.source.PostsRemoteDataSourceImpl
import com.example.reddit.posts.data.repo.PostsRepoImpl
import com.example.reddit.posts.domain.PostsRepo
import com.example.reddit.posts.presentation.PostsActivity
import com.example.reddit.posts.presentation.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
class PostsModule {

    @Provides
    fun providePostsRemoteDataSource(
         redditApi: RedditApi
    ):PostsRemoteDataSource {
        return PostsRemoteDataSourceImpl(redditApi)
    }

    @Provides
    fun providePostsRepo(
        remoteSource: PostsRemoteDataSource
    ): PostsRepo {
        return PostsRepoImpl(remoteSource)
    }
}