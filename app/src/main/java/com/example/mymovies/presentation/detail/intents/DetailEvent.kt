package com.example.mymovies.presentation.detail.intents

sealed class DetailEvent {
    data class FetchMovieById(val id: Int?) : DetailEvent()
}