package com.hfad.moviewishlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.adapters.MovieAdapter
import com.hfad.moviewishlist.databinding.FragmentMovieSearchBinding
import com.hfad.moviewishlist.utils.Constants.Companion.SEARCH_MOVIE_TIME_DELAY
import com.hfad.moviewishlist.utils.Resources
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieSearchFragment(private val searchToolbar: MaterialToolbar) : Fragment() {
    private lateinit var binding: FragmentMovieSearchBinding
    var searchMovieJob: Job? = null
//    private lateinit var searchAdapter: MovieAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((requireActivity().application as MovieApplication).repository)
    }
    private val searchAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieSearchBinding.inflate(layoutInflater, container, false)

        ((activity as AppCompatActivity)).menuInflater.inflate(R.menu.search_bar_menu, searchToolbar.menu)

        searchToolbar.setNavigationIcon(R.drawable.ic_arrow_back)


        setUpRecyclerview()

        binding.searchView.addTextChangedListener { editable ->
            searchMovieJob?.cancel()
            searchMovieJob = lifecycleScope.launch {
                delay(SEARCH_MOVIE_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchMovie(editable.toString())
                    }
                }
            }
        }
        viewModel.searchmovie.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resources.Success -> {
                    hideProgressBor()
                    response.data?.let { movieResponse ->
                        searchAdapter.submitList(movieResponse)
                    }
                }
                is Resources.Error -> {
                    hideProgressBor()
                    response.message?.let {
                        "Error occurred"
                    }
                }

                is Resources.Loading -> {
                    showProgressBor()
                }
            }
        })
        return binding.root
    }

    private fun hideProgressBor() {
        binding.searchProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBor() {
        binding.searchProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerview() {
            binding.searchRecyclerView.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
    }

//    val recyclerview = binding.searchRecyclerView
//        recyclerview.adapter = searchAdapter
//        recyclerview.layoutManager = LinearLayoutManager(requireContext())

}