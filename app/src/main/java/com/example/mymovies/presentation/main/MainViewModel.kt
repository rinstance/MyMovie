package com.example.mymovies.presentation.main

import androidx.lifecycle.ViewModel
import com.example.mymovies.presentation.screens.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val router: Router
) : ViewModel() {

    fun toSearch() {
        router.navigateTo(Screens.MovieListScreen)
    }

}