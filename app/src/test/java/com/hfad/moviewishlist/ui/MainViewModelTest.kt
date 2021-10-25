package com.hfad.moviewishlist.ui

import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.hfad.moviewishlist.repository.FakeMovieRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainViewModelTest {
//    private lateinit var viewModel: MainViewModel
//
//    @Before
//    fun setUp() {
//        viewModel = MainViewModel(FakeMovieRepository())
//    }

    @Test
    fun addNewMovie_setsNewMovie() {
        val viewModel = MainViewModel(ApplicationProvider.getApplicationContext())
    }
}