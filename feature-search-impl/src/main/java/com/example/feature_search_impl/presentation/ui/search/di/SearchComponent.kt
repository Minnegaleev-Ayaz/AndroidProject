package com.example.feature_search_impl.presentation.ui.search.di

import androidx.fragment.app.Fragment
import com.example.feature_search_impl.presentation.ui.search.SearchFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SearchModule::class, ViewModelModule::class]
)
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): SearchComponent
    }
    fun inject(fragment: SearchFragment)
}