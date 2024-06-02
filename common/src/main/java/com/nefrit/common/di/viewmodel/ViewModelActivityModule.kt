package com.nefrit.common.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelActivityModule {
    @Binds
    abstract fun bindViewModelFactory_to_ViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}