package com.example.mymovies.presentation.search.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovies.domain.models.Movie

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.title == newItem.title

}
