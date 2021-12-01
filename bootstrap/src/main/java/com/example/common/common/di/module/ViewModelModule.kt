package com.example.common.common.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.common.common.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}