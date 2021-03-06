package com.example.reddit.favorite.data.local.source

import com.example.reddit.favorite.data.local.PostsDao
import com.example.reddit.posts.data.model.PostData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class FavouriteLocalDataSourceImpl @Inject constructor(
    private val postsDao: PostsDao
): FavouriteLocalDataSource {

    override fun addFavourite(postData: PostData): Completable = postsDao.insert(postData)

    override fun fetchFavourites(): Observable<List<PostData>> = postsDao.fetchFavourites()

    override fun deleteFavourite(postData: PostData) = postsDao.deleteFavourite(postData)

    override fun deleteAllFavourites(): Completable = postsDao.deleteAll()
}