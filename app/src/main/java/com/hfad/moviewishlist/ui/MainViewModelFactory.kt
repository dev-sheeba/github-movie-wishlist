package com.hfad.moviewishlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.hfad.moviewishlist.repository.MovieRepository

class MainViewModelFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(movieRepository) as T
    }
}