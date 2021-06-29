package com.example.mymovies.presentation

import android.app.Application
import com.example.mymovies.presentation.di.AppComponent
import com.example.mymovies.presentation.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .context(applicationContext)
                .build()
                .inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
