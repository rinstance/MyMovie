package com.example.mymovies.domain.interactors

import com.example.mymovies.data.interfaces.MovieRepository
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.utils.DownloadImageHelper
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmResults
import javax.inject.Inject

class MovieInteractor @Inject constructor(
        private val repository: MovieRepository,
        private val downloadImageHelper: DownloadImageHelper
) {

    fun findByTitle(title: String): Observable<List<Movie>> = repository.findByTitle(title).toObservable()

    fun findById(id: Int): Single<Movie> = repository.findById(id)

    fun setDrawableToMovie(movie: Movie): Movie {
        movie.drawable = downloadImageHelper.getDrawable(movie.posterPath)
        return movie
    }

}