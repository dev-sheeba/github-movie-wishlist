package com.hfad.moviewishlist.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.adapters.MovieAdapter
import com.hfad.moviewishlist.databinding.FragmentMovieHomeBinding


class MovieHomeFragment(
    private val toolbar: MaterialToolbar,
    private val listenerNavigateSearch: OnNavigateSearchClickListener
) : Fragment() {

    private lateinit var binding: FragmentMovieHomeBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((requireActivity().application as MovieApplication).repository)
    }

    private val myAdapter: MovieAdapter by lazy { MovieAdapter() }

    interface OnNavigateSearchClickListener {
        fun onFabSearchClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieHomeBinding.inflate(layoutInflater, container, false)
//        setUpRecyclerView()
//
//        viewModel.movies.observe(viewLifecycleOwner, Observer {
//            when(it) {
//                is Resources.Success -> {
//
//                }
//            }
//        })
//
        ((activity as AppCompatActivity)).menuInflater.inflate(R.menu.app_bar_menu, toolbar.menu)

        toolbar.setNavigationIcon(R.drawable.ic_nav_icon)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sort -> true
                else -> false
            }
        }

        binding.fabAddMovie.setOnClickListener {
            listenerNavigateSearch.onFabSearchClick()
        }
        return binding.root
    }

//    override fun onCheckBoxClick(movie: Movie, isChecked: Boolean) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onMovieFavClicked(movie: Movie, isChecked: Boolean) {
//        TODO("Not yet implemented")
//    }

    private fun setUpRecyclerView() {
        binding.movieRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
