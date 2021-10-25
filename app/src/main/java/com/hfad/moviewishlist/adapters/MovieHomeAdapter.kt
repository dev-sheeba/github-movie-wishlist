package com.hfad.moviewishlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.moviewishlist.databinding.MovieItemBinding
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.ui.MovieDetailFragment

class LMovieHomeAdapter(private val listener: OnMovieActionListener) : ListAdapter<Movie,MovieHomeAdapter.MovieHomeViewHolder>(ListsComparator()) {

    interface OnMovieActionListener{
        fun onCheckBoxClick(movie: Movie, isChecked: Boolean)
        fun onMovieFavClicked(movie: Movie, isChecked: Boolean)
    }

    inner class MovieHomeViewHolder(private val binding: MovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: Movie) {
                binding.apply {
                    movieTitle.text = movie.title
                    movieCheckBox.isChecked = movie.isCompleted
                    movieFav.isChecked = movie.isFavourite

                    movieCheckBox.setOnClickListener {
                        if (movieCheckBox.isChecked) {
                            movieCheckBox.paint.isStrikeThruText = movie.isCompleted
                        }

                        listener.onCheckBoxClick(movie,movieCheckBox.isChecked)
                    }

                    movieFav.setOnClickListener {
                        listener.onMovieFavClicked(movie, movieFav.isChecked)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ListsComparator: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}