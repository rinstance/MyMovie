package com.example.mymovies.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.mymovies.R
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.presentation.BaseFragment
import com.example.mymovies.presentation.detail.intents.DetailEvent
import com.example.mymovies.presentation.detail.intents.DetailState
import com.example.mymovies.presentation.viewModels
import kotlinx.android.synthetic.main.fragment_details.*

private const val ID = "movie_id"

class DetailFragment : BaseFragment(R.layout.fragment_details) {
    private val model: DetailViewModel by viewModels()

    companion object {
        fun getInstance(id: Int): DetailFragment = DetailFragment().apply {
            arguments = bundleOf(ID to id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovie(arguments?.getInt(ID))
    }

    private fun fetchMovie(id: Int?) {
        model.obtainEvent(DetailEvent.FetchMovieById(id))
    }

    override fun observeOnStates() {
        model.stateSubject.subscribe {
            when (it) {
                is DetailState.SubmitMovie -> displayMovie(it.movie)
                is DetailState.NoneId -> showErrorText()
            }
        }.isDisposed
    }

    private fun displayMovie(movie: Movie) {
        movie.also {
            title.text = it.title
            overview.text = it.overview
            it.drawable?.let {
                image_preview.setImageDrawable(it)
            }
        }
    }

    private fun showErrorText() {
        text_some_error.visibility = View.VISIBLE
    }
}