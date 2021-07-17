package com.hfad.moviewishlist.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.repository.MovieService
import com.hfad.moviewishlist.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class MainViewModel(private val movieService: MovieService) : ViewModel() {

    val movies: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()
    val searchmovie: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()

    fun getMovies() = viewModelScope.launch {
        movies.postValue(Resources.Loading())
        val response = movieService.getMovie()
        movies.postValue(handleMovieResponse(response))
    }

    fun searchMovie(searhQuery: String) = viewModelScope.launch(Dispatchers.Main.immediate) {
        searchmovie.postValue(Resources.Loading())
        try {
            val response = movieService.searchMovie(searhQuery)
            searchmovie.postValue(handleSearchMovieResponse(response))
        } catch (e: Exception) {
            Timber.e(e, e.message)
            searchmovie.postValue(Resources.Error(e.message))
        }

    }

    private fun handleMovieResponse(response: Response<List<Movie>>): Resources<List<Movie>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resources.Success(it)
            }
        }
        return Resources.Error(response.message())
    }

    private fun handleSearchMovieResponse(response: Response<List<Movie>>): Resources<List<Movie>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resources.Success(it)
            }
        }
        return Resources.Error(response.message())
    }
}