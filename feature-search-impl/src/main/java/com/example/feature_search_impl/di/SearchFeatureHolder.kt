package com.example.feature_search_impl.di

import com.example.feature_search_impl.SearchFeatureRouter
import com.example.panda_score_api.remote.ExceptionHandlerDelegate
import com.example.panda_score_api.remote.PandasScoreApi
import com.example.room_api.AppDatabase
import com.nefrit.common.di.FeatureApiHolder
import com.nefrit.common.di.FeatureContainer
import com.nefrit.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class SearchFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: SearchFeatureRouter,
    private val api: PandasScoreApi,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val appDatabase: AppDatabase
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val searchFeatureDependencies =
            DaggerSearchFeatureComponent_SearchFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerSearchFeatureComponent.builder()
            .withDependencies(searchFeatureDependencies)
            .exceptionHandlerDelegate(exceptionHandlerDelegate)
            .appDatabase(appDatabase)
            .api(api)
            .router(router)
            .build()

    }

}