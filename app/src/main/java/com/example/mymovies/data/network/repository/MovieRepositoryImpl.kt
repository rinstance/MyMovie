package com.example.mymovies.data.network.repository

import com.example.mymovies.data.interfaces.MovieRepository
import com.example.mymovies.domain.models.Movie
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi
) : MovieRepository {

    override fun findByTitle(title: String): Single<List<Movie>> =
            api.getByTitle(title).map { it.results }

    override fun findById(id: Int): Single<Movie> =
            api.getById(id)

}
