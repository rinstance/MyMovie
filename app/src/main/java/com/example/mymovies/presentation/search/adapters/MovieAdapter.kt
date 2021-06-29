package com.example.mymovies.presentation.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.domain.interactors.MovieInteractor
import com.example.mymovies.domain.models.Movie
import com.example.mymovies.utils.DownloadImageHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.*
import javax.inject.Inject

class MovieAdapter @Inject constructor(
        private val downloadImageHelper: DownloadImageHelper
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {
    private lateinit var itemClick: (Movie) -> Unit
    private lateinit var longClick: (Movie) -> Unit

    fun setItemClick(itemClick: (Movie) -> Unit) {
        this.itemClick = itemClick
    }

    fun setLongClick(longClick: (Movie) -> Unit) {
        this.longClick = longClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movie) {
            setBody(movie)
            setItemClickListeners(movie)
        }

        private fun setBody(movie: Movie?) {
            movie?.let {
                title.text = it.title
                description.text = it.overview
                date.text = it.releaseDate
                rate.text = it.popularity.toString()
                downloadImageHelper.loadImage(movie_poster, it.posterPath)
            }
        }

        private fun setItemClickListeners(movie: Movie) {
            itemView.setOnClickListener { itemClick(movie) }
            itemView.setOnLongClickListener {
                longClick(movie)
                true
            }
        }

    }
}