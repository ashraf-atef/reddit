package com.example.common.application

import com.example.reddit.BuildConfig
import com.example.common.di.component.AppComponent
import com.example.common.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .apiUrl(BuildConfig.SERVER_URL)
            .build()
        return appComponent
    }
}