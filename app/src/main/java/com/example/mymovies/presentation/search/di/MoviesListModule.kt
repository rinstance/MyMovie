package com.example.mymovies.presentation.search.di

import androidx.lifecycle.ViewModel
import com.example.mymovies.presentation.di.viewmodel.ViewModelKey
import com.example.mymovies.presentation.search.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MoviesListModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}