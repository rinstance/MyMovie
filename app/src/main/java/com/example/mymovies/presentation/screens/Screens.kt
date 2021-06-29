package com.example.mymovies.presentation.screens

import androidx.fragment.app.Fragment
import com.example.mymovies.presentation.detail.DetailFragment
import com.example.mymovies.presentation.search.MoviesListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object MovieListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = MoviesListFragment()
    }

    class DetailScreen(private val id: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailFragment.getInstance(id)
    }
}