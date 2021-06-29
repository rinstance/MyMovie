package com.example.mymovies.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import com.example.mymovies.R
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.presentation.BaseFragment
import com.example.mymovies.presentation.search.adapters.MovieAdapter
import com.example.mymovies.presentation.search.intents.SearchEvent
import com.example.mymovies.presentation.search.intents.SearchState
import com.example.mymovies.presentation.viewModels
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesListFragment : BaseFragment(R.layout.fragment_movies) {

    @Inject
    lateinit var adapter: MovieAdapter

    private val model: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setSearchView()
    }

    override fun observeOnStates() {
        model.stateSubject.subscribe {
            when (it) {
                is SearchState.SubmitMovies -> submitSearchedMovies(it.movies)
                is SearchState.Error -> showToast(R.string.error)
            }
        }.isDisposed
    }

    private fun showToast(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

    private fun submitSearchedMovies(movies: List<Movie>) {
        adapter.submitList(movies)
    }

    private fun setSearchView() {
        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean { return true }

            override fun onQueryTextChange(newText: String): Boolean {
                model.obtainEvent(SearchEvent.SearchByTitle(newText))
                return true
            }
        })
    }

    private fun setAdapter() {
        adapter.apply {
            setItemClick { model.obtainEvent(SearchEvent.NavigateToDetail(it.id)) }
            setLongClick { saveToFavourite(it) }
            search_recycler.adapter = this
        }
    }

    private fun saveToFavourite(movie: Movie) {
        model.obtainEvent(SearchEvent.SaveToFavourite(movie))
    }
}