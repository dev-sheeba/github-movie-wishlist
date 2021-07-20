package com.hfad.moviewishlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.moviewishlist.databinding.SearchMovieItemBinding
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.utils.Constants.Companion.BASE_URL
import com.hfad.moviewishlist.utils.Constants.Companion.IMAGE_BASE_URL

class MovieAdapter() :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(ListsComparator()) {

//    interface OnMovieActionListener {
//        fun onCheckBoxClick(movie: Movie, isChecked: Boolean)
//        fun onMovieFavClicked(movie: Movie, isChecked: Boolean)
//    }

    inner class MovieViewHolder(private val binding: SearchMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                if (movie.posterPath != null) {
                    Glide.with(moviePoster).load(IMAGE_BASE_URL + movie.posterPath)
                        .into(moviePoster)
                }
                movieTitle.text = movie.title
                movieDescription.text = movie.overview
                movieReleaseDate.text = movie.releaseDate
                movieVoteAverage.text = movie.voteAverage.toString()
            }
        }
    }


    class ListsComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = SearchMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}