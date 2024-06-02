package com.example.feature_search_impl.di

import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.SearchFeatureRouter
import com.example.feature_search_impl.presentation.ui.player.di.PlayerComponent
import com.example.feature_search_impl.presentation.ui.player.di.PlayerFromTeamComponent
import com.example.feature_search_impl.presentation.ui.search.di.SearchComponent
import com.example.feature_search_impl.presentation.ui.team.di.TeamComponent
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.example.room_api.AppDatabase
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.scope.FeatureScope
import com.nefrit.common.notification.NotificationManagerWrapper
import com.nefrit.common.utils.DateFormatter
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [SearchFeatureModule::class],
    dependencies = [SearchFeatureDependencies::class]
)
interface SearchFeatureComponent : SearchFeatureApi {
    fun searchComponentFactory(): SearchComponent.Factory
    fun playerComponentFactory(): PlayerComponent.Factory
    fun teamComponentFactory(): TeamComponent.Factory
    fun playerFromTeamComponentFactory(): PlayerFromTeamComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun api(pandasScoreApi: PandasScoreApi): Builder

        @BindsInstance
        fun exceptionHandlerDelegate(exceptionHandlerDelegate: ExceptionHandlerDelegate): Builder
        @BindsInstance
        fun appDatabase(appDatabase: AppDatabase):Builder
        @BindsInstance
        fun router(searchFeatureRouter: SearchFeatureRouter): Builder
        fun withDependencies(deps: SearchFeatureDependencies): Builder
        fun build(): SearchFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface SearchFeatureDependenciesComponent : SearchFeatureDependencies
}