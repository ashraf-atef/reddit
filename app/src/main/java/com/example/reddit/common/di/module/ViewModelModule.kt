package com.example.reddit.common.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.reddit.common.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}