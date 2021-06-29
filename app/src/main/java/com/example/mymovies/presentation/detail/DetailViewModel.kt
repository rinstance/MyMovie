package com.example.mymovies.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.mymovies.domain.interactors.MovieInteractor
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.presentation.detail.intents.DetailEvent
import com.example.mymovies.presentation.detail.intents.DetailState
import com.example.mymovies.utils.DownloadImageHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val movieInteractor: MovieInteractor,
        private val downloadImageHelper: DownloadImageHelper
) : ViewModel() {
    private val disposables = CompositeDisposable()
    val stateSubject = PublishSubject.create<DetailState>()

    fun obtainEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.FetchMovieById -> checkMovieId(event.id)
        }
    }

    private fun checkMovieId(id: Int?) {
        if (id != null) {
            fetchMovie(id)
        } else {
            stateError()
        }
    }

    private fun stateError() {
        stateSubject.onNext(DetailState.NoneId)
    }

    private fun fetchMovie(id: Int) {
        disposables.add(
                movieInteractor.findById(id)
                        .map { movieInteractor.setDrawableToMovie(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            stateSubject.onNext(DetailState.SubmitMovie(it))
                        }, {
                            stateError()
                        })
        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}