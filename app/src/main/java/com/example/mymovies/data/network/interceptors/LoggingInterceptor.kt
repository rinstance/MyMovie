package com.example.mymovies.data.network.interceptors

import com.example.mymovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import kotlin.jvm.Throws

class LoggingInterceptor(
        private val loggingInterceptor: HttpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE)
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response =
            loggingInterceptor.intercept(chain)
}