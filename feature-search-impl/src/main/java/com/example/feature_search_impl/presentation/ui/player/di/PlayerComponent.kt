package com.example.feature_search_impl.presentation.ui.player.di

import androidx.fragment.app.Fragment
import com.example.feature_search_impl.presentation.ui.player.PlayerFragment
import com.example.feature_search_impl.presentation.ui.player.PlayerFromTeamFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [PlayerModule::class, ViewModelModule::class]
)
interface PlayerComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance fragment: Fragment
        ): PlayerComponent
    }
    fun inject(fragment: PlayerFragment)
}