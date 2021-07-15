package com.hfad.moviewishlist.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.databinding.FragmentMovieHomeBinding


class MovieHomeFragment(private val toolbar: MaterialToolbar) : Fragment() {

    private lateinit var binding: FragmentMovieHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieHomeBinding.inflate(layoutInflater, container, false)

        ((activity as AppCompatActivity)).menuInflater.inflate(R.menu.app_bar_menu, toolbar.menu)

        toolbar.setNavigationIcon(R.drawable.ic_nav_icon)

        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.sort -> true
                else -> false
            }
        }

        return binding.root
    }

}