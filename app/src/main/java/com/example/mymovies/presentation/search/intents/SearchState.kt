package com.example.mymovies.presentation.search.intents

import com.example.mymovies.domain.models.Movie

sealed class SearchState {
    data class SubmitMovies(val movies: List<Movie>): SearchState()
    object Error: SearchState()
}