package com.hfad.moviewishlist.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.databinding.ActivityMainBinding
import com.hfad.moviewishlist.model.Movie


class MainActivity : AppCompatActivity(), MovieHomeFragment.OnNavigateSearchClickListener, MovieSearchFragment.OnNavigateMovieItemClickListener, MovieDetailFragment.OnNavigateSAveClickListener{

    private lateinit var binding: ActivityMainBinding
    private var fragmentContainer: FrameLayout? = null
    lateinit var toolbar: MaterialToolbar
    lateinit var searchToolbar: MaterialToolbar
    lateinit var detailToolbar: MaterialToolbar

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        searchToolbar = binding.toolbar
        detailToolbar = binding.toolbar


        searchToolbar.setNavigationOnClickListener {

        }

        toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        fragmentContainer = binding.fragmentContainer

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, MovieHomeFragment(toolbar, this))
            .commit()

    }

    override fun onFabSearchClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MovieSearchFragment(searchToolbar, this))
            .commit()
    }


    override fun onMovieItemClick(movie: Movie) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MovieDetailFragment(movie, detailToolbar, this))
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MovieHomeFragment(toolbar, this))
            .commit()
    }

}
