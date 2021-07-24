package com.hfad.moviewishlist.ui

import com.hfad.moviewishlist.repository.FakeMovieRepository
import org.junit.Assert.*
import org.junit.Before

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(FakeMovieRepository())
    }
}