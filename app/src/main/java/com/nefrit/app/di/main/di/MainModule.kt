package com.nefrit.app.di.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nefrit.app.di.main.MainViewModel
import com.nefrit.common.data.storage.PreferencesImpl
import com.nefrit.common.di.viewmodel.ViewModelActivityModule
import com.nefrit.common.di.viewmodel.ViewModelKey
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelActivityModule::class
    ]
)
class MainModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideViewModel(preferencesImpl: PreferencesImpl): ViewModel {
        return MainViewModel(preferencesImpl)
    }

    @Provides
    fun provideViewModelCreator(activity: AppCompatActivity, viewModelFactory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProviders.of(activity, viewModelFactory).get(MainViewModel::class.java)
    }
}