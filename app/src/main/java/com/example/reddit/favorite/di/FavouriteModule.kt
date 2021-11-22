package com.example.reddit.favorite.di

import android.content.Context
import com.example.reddit.favorite.data.FavouriteRepoImpl
import com.example.reddit.favorite.data.local.LocalDatabase
import com.example.reddit.favorite.data.local.PostsDao
import com.example.reddit.favorite.data.local.source.FavouriteLocalDataSource
import com.example.reddit.favorite.data.local.source.FavouriteLocalDataSourceImpl
import com.example.reddit.favorite.domain.repo.FavouriteRepo
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class FavouriteModule {

    @Provides
    @Reusable
    fun provideDatabase(context: Context): LocalDatabase = LocalDatabase.getInstance(context)

    @Provides
    fun providePostsDAO(localDatabase: LocalDatabase): PostsDao = localDatabase.postDao()

    @Provides
    fun provideFavouriteLocalDataSource(postsDao: PostsDao): FavouriteLocalDataSource =
        FavouriteLocalDataSourceImpl(postsDao)

    @Provides
    fun provideFavouriteRepo(favouriteLocalDataSource: FavouriteLocalDataSource) : FavouriteRepo =
        FavouriteRepoImpl(favouriteLocalDataSource)
}