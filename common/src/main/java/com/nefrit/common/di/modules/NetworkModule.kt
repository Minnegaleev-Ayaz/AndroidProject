package com.nefrit.common.di.modules

import com.nefrit.common.core.config.AppProperties
import com.nefrit.common.core.config.NetworkProperties
import com.nefrit.common.di.scope.ApplicationScope
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideNetworkProperties(appProperties: AppProperties): NetworkProperties {
        return appProperties.networkProperties()
    }

}