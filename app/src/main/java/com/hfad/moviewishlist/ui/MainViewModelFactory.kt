package com.hfad.moviewishlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.moviewishlist.repository.MovieService

class MainViewModelFactory(private val movieService: MovieService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(movieService) as T
    }
}