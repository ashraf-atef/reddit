package com.example.restaurant.common.di.module

import com.example.restaurant.common.presentationLayer.rx.schedulers.SchedulersProvider
import com.example.restaurant.common.presentationLayer.rx.schedulers.SchedulersProviderImpl
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    fun provideSchedulersService(): SchedulersProvider = SchedulersProviderImpl()
}
