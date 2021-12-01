package com.example.reddit.posts.di

import com.example.reddit.posts.data.remote.api.RedditApi
import com.example.reddit.posts.data.remote.source.PostsRemoteDataSource
import com.example.reddit.posts.data.remote.source.PostsRemoteDataSourceImpl
import com.example.reddit.posts.data.repo.PostsRepoImpl
import com.example.reddit.posts.domain.PostsRepo
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class PostsModule {

    @Provides
    fun providesRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }

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