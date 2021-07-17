package com.hfad.moviewishlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.moviewishlist.databinding.MovieItemBinding
import com.hfad.moviewishlist.databinding.SearchMovieItemBinding
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.ui.MovieHomeFragment

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
                movieTitle.text = movie.title
                movieDescription.text = movie.overview
                movieReleaseDate.text = movie.release_date
                movieVoteAverage.text = movie.vote_average.toString()
            }
        }
    }


    class ListsComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
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