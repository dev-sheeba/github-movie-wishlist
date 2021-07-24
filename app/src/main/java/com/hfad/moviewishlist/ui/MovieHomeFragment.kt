package com.hfad.moviewishlist.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.adapters.MovieHomeAdapter
import com.hfad.moviewishlist.databinding.FragmentMovieHomeBinding
import com.hfad.moviewishlist.model.Movie


class MovieHomeFragment(
    private val toolbar: MaterialToolbar,
    private val listenerNavigate: OnNavigateSearchClickListener
) : Fragment(), MovieHomeAdapter.OnMovieActionListener {

    private lateinit var binding: FragmentMovieHomeBinding

    private var allMovies: LiveData<List<Movie>>? = null

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((requireActivity().application as MovieApplication).repository)
    }

    private val movieHomeAdapterSearch: MovieHomeAdapter by lazy { MovieHomeAdapter(this) }

    interface OnNavigateSearchClickListener {
        fun onFabSearchClick()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieHomeBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()

        ((activity as AppCompatActivity)).menuInflater.inflate(R.menu.app_bar_menu, toolbar.menu)

        toolbar.setNavigationIcon(R.drawable.ic_nav_icon)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sort -> true
                else -> false
            }
        }

        binding.fabAddMovie.setOnClickListener {
            listenerNavigate.onFabSearchClick()
        }
        observeAllTasks()

        return binding.root
    }

    private fun observeAllTasks() {
        allMovies = viewModel.allMovies()
        allMovies?.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                movieHomeAdapterSearch.submitList(movies)
            }
        })
    }

    override fun onCheckBoxClick(movie: Movie, isChecked: Boolean) {
        viewModel.onMovieIsCheckedChanged(movie, isChecked)
    }

    override fun onMovieFavClicked(movie: Movie, isChecked: Boolean) {
        viewModel.onMovieIsCheckedChanged(movie, isChecked)
    }

    private fun setUpRecyclerView() {
        binding.movieRecyclerView.apply {
            adapter = movieHomeAdapterSearch
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}
