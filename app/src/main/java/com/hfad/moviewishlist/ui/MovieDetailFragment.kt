package com.hfad.moviewishlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}