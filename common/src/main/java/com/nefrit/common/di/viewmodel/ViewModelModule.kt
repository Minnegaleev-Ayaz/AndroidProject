package com.nefrit.common.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.kpfu.itis.android_inception_23.utils.AssistedViewModelFactory
import com.kpfu.itis.android_inception_23.utils.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindDaggerFactory_to_ViewModelFactory(impl: DaggerViewModelFactory): ViewModelProvider.Factory
}