package com.hfad.moviewishlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.adapters.MovieHomeAdapter
import com.hfad.moviewishlist.databinding.FragmentMovieDetailBinding
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.utils.Constants


class MovieDetailFragment(
    val movie: Movie,
    private val detailToolbar: MaterialToolbar,
    private val listenerNavigate: MovieDetailFragment.OnNavigateSAveClickListener
) : Fragment() {

    interface OnNavigateSAveClickListener {
        fun onSaveClick()
    }

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as MovieApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)

        ((activity as AppCompatActivity)).menuInflater.inflate(
            R.menu.detail_menu_tool_bar,
            detailToolbar.menu
        )

        detailToolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        binding.apply {
            if (movie.posterPath != null) {
                Glide.with(detailMoviePoster).load(Constants.IMAGE_BASE_URL + movie.posterPath)
                    .into(detailMoviePoster)
            }
            detailMovieTitle.text = movie.title
            detailMovieDescription.text = movie.overview
            detailMovieReleaseDate.text = movie.releaseDate
            detailMovieVoteAverage.text = movie.voteAverage.toString()

            detailSaveButton.setOnClickListener {
                val title = movie.title
                val updatedMovie = movie.copy(title = title, isCompleted = false, isFavourite = false)
                viewModel.saveMovie(updatedMovie)
                listenerNavigate.onSaveClick()
            }
        }

        return binding.root
    }


}