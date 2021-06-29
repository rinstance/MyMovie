package com.example.mymovies.data.interfaces

import com.example.mymovies.domain.models.Movie
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {
    fun findByTitle(title: String): Single<List<Movie>>
    fun findById(id: Int): Single<Movie>
}