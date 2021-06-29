package com.example.mymovies.presentation.search.intents

import com.example.mymovies.domain.models.Movie

sealed class SearchEvent {
    data class SearchByTitle(val title: String): SearchEvent()
    data class NavigateToDetail(val id: Int): SearchEvent()
    data class SaveToFavourite(val movie: Movie): SearchEvent()
}