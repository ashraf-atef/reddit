package com.example.common.common.di.module

import com.example.common.common.presentation.schedulers.SchedulersProvider
import com.example.common.common.presentation.schedulers.SchedulersProviderImpl
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    fun provideSchedulersService(): SchedulersProvider = SchedulersProviderImpl()
}
