package com.example.mymovies.presentation.detail.di

import androidx.lifecycle.ViewModel
import com.example.mymovies.presentation.detail.DetailViewModel
import com.example.mymovies.presentation.di.viewmodel.ViewModelKey
import com.example.mymovies.presentation.search.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindMoviesViewModel(viewModel: DetailViewModel): ViewModel
}