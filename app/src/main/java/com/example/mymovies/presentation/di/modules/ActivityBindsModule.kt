package com.example.mymovies.presentation.di.modules

import com.example.mymovies.presentation.main.MainActivity
import com.example.mymovies.presentation.di.scopes.MainScope
import com.example.mymovies.presentation.main.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindsModule {
    @MainScope
    @ContributesAndroidInjector(modules = [FragmentBindsModule::class, MainModule::class])
    fun contributeMainActivity(): MainActivity
}

