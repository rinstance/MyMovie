package com.example.mymovies.presentation.main.di

import androidx.lifecycle.ViewModel
import com.example.mymovies.presentation.di.viewmodel.ViewModelKey
import com.example.mymovies.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMoviesViewModel(viewModel: MainViewModel): ViewModel
}