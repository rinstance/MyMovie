package com.example.mymovies.presentation.di

import android.app.Application
import android.content.Context
import com.example.mymovies.data.di.ApiModule
import com.example.mymovies.data.di.RepositoryModule
import com.example.mymovies.presentation.App
import com.example.mymovies.presentation.di.modules.ActivityBindsModule
import com.example.mymovies.presentation.di.modules.CiceroneModule
import com.example.mymovies.presentation.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    RepositoryModule::class,
    ApiModule::class,
    CiceroneModule::class,
    ViewModelModule::class,
    ActivityBindsModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}