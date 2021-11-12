package com.example.reddit.common.di.component

import android.app.Application
import com.example.reddit.common.application.BaseApplication
import com.example.reddit.common.constants.SERVER_URL_KEY
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