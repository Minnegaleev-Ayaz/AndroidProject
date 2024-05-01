package com.example.feature_predict_impl.data.di

import com.example.feature_predict_impl.BuildConfig
import com.example.feature_predict_impl.data.remote.PandasScoreApi
import com.example.feature_predict_impl.data.remote.interceptors.TokenInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nefrit.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PredictNetworkModule {
    @Provides
    @FeatureScope
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
    @FeatureScope
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            /*.addInterceptor(TokenInterceptor())*/
            .build()
    }
}