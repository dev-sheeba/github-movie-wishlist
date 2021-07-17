package com.hfad.moviewishlist.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.moviewishlist.R
import com.hfad.moviewishlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MovieHomeFragment.OnNavigateSearchClickListener {

    private lateinit var binding: ActivityMainBinding
    private var fragmentContainer: FrameLayout? = null
    lateinit var toolbar: MaterialToolbar

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar


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
            .replace(R.id.fragment_container, MovieSearchFragment())
            .commit()
    }
}
