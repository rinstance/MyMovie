package com.example.mymovies.data.di

import com.example.mymovies.BuildConfig
import com.example.mymovies.data.interfaces.MovieRepository
import com.example.mymovies.data.network.interceptors.ApiConstInterceptor
import com.example.mymovies.data.network.interceptors.LoggingInterceptor
import com.example.mymovies.data.network.repository.MovieApi
import com.example.mymovies.data.network.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .client(client)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(LoggingInterceptor())
                    .addInterceptor(ApiConstInterceptor())
                    .build()

    @Provides
    @Singleton
    fun getMovieApi(retrofit: Retrofit): MovieApi =
            retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideSubject(): PublishSubject<String> = PublishSubject.create()
}

@Module
interface ApiModule {
    @Singleton
    @Binds
    fun provideMovieDatabase(impl: MovieRepositoryImpl): MovieRepository
}