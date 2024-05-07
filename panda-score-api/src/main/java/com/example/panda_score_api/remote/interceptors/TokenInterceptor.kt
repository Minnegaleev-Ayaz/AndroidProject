package com.example.feature_predict_impl.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url().newBuilder()
            .addQueryParameter("token", "jHpOJg5KP8bOwd0aU2pM6abcnGo6Us85zULTVKTJKAUfOvcM0cg")

            .build()

        val requestBuilder = chain.request().newBuilder().url(newUrl).addHeader("accept","application/json")

        return chain.proceed(requestBuilder.build())
    }
}