package com.example.feature_search_impl.presentation.ui.search.di

import androidx.lifecycle.ViewModel
import com.example.feature_search_impl.presentation.ui.search.SearchViewModel
import com.nefrit.common.di.scope.FeatureScope
import com.nefrit.common.di.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SearchModule {
    @Provides
    @[IntoMap ViewModelKey(SearchViewModel::class)]
    fun provideSearchViewModel(viewModel: SearchViewModel):ViewModel = viewModel
}