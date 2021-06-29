package com.example.mymovies.data.network.interceptors

import com.example.mymovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiConstInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return original.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.KEY)
                .addQueryParameter("language", BuildConfig.SEARCH_LANGUAGE)
                .build()
                .let {
                    chain.proceed(original.newBuilder().url(it).build())
                }
    }
}