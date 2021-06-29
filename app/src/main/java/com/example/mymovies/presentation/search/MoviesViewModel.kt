package com.example.mymovies.presentation.search

import androidx.lifecycle.ViewModel
import com.example.mymovies.domain.interactors.MovieInteractor
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.presentation.screens.Screens
import com.example.mymovies.presentation.search.intents.SearchEvent
import com.example.mymovies.presentation.search.intents.SearchState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
        private val movieInteractor: MovieInteractor,
        private val router: Router
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val titleSubject = PublishSubject.create<String>()
    val stateSubject = PublishSubject.create<SearchState>()

    init {
        fetchMovies()
    }

    fun obtainEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchByTitle -> searchByTitle(event.title)
            is SearchEvent.NavigateToDetail -> router.navigateTo(Screens.DetailScreen(event.id))
        }
    }

    private fun searchByTitle(title: String) {
        if (title.isNotEmpty()) {
            titleSubject.onNext(title)
        }
    }

    private fun fetchMovies() {
        disposables.add(titleSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .switchMap { movieInteractor.findByTitle(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    stateSubject.onNext(SearchState.SubmitMovies(it))
                }, {
                    stateSubject.onNext(SearchState.Error)
                })
        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}