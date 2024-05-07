package com.example.feature_predict_impl.data.di

import com.example.feature_predict_impl.data.remote.interceptors.TokenInterceptor
import com.example.panda_score_api.BuildConfig
import com.example.panda_score_api.remote.PandasScoreApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nefrit.common.core.config.NetworkProperties
import com.nefrit.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiNetworkModule {
    @Provides
    @ApplicationScope
    fun providePandasScoreApi(okHttpClient: OkHttpClient): PandasScoreApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PANDAS_SCORE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(PandasScoreApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(networkProperties:NetworkProperties): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(networkProperties.connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(networkProperties.writeTimeout, TimeUnit.SECONDS)
            .readTimeout(networkProperties.readTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(TokenInterceptor())
            .build()
    }
}