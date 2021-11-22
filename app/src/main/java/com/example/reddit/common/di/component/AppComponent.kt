package com.example.reddit.common.di.component

import android.app.Application
import com.example.reddit.common.application.BaseApplication
import com.example.reddit.common.constants.SERVER_URL_KEY
import com.example.reddit.common.di.module.ViewModelModule
import com.example.reddit.favorite.di.FavouriteModule
import com.example.reddit.favorite.di.FavouritesUiModule
import com.example.reddit.posts.di.PostsModule
import com.example.reddit.posts.di.PostsUiModule
import com.example.restaurant.common.di.module.AppModule
import com.example.restaurant.common.di.module.NetModule
import com.example.restaurant.common.di.module.SchedulersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetModule::class,
        SchedulersModule::class,
        PostsModule::class,
        PostsUiModule::class,
        FavouriteModule::class,
        FavouritesUiModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(baseApplication: BaseApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        @Named(SERVER_URL_KEY)
        fun apiUrl(apiUrl: String): Builder

        fun build(): AppComponent
    }
}