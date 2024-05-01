package com.example.feature_predict_impl.data.remote.interceptors

import com.example.feature_predict_impl.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val updatedRequest = originalRequest.newBuilder()
            .addHeader("token", "jHpOJg5KP8bOwd0aU2pM6abcnGo6Us85zULTVKTJKAUfOvcM0cg")
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(updatedRequest)
    }
}