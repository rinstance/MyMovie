package com.example.mymovies.presentation.di.modules

import com.example.mymovies.presentation.detail.DetailFragment
import com.example.mymovies.presentation.detail.di.DetailModule
import com.example.mymovies.presentation.di.scopes.PresentationsMovieScope
import com.example.mymovies.presentation.search.MoviesListFragment
import com.example.mymovies.presentation.search.di.MoviesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBindsModule {
    @PresentationsMovieScope
    @ContributesAndroidInjector(modules = [MoviesListModule::class])
    fun contributeSearchFragment(): MoviesListFragment

    @PresentationsMovieScope
    @ContributesAndroidInjector(modules = [DetailModule::class])
    fun contributeDetailFragment(): DetailFragment
}