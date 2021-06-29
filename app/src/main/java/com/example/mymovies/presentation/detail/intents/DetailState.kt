package com.example.mymovies.presentation.detail.intents

import com.example.mymovies.domain.models.Movie

sealed class DetailState {
    data class SubmitMovie(val movie: Movie) : DetailState()
    object NoneId : DetailState()
}