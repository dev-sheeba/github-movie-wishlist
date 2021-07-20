package com.hfad.moviewishlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import com.hfad.moviewishlist.repository.DefaultMovieRepository
import com.hfad.moviewishlist.repository.MovieRepository
import com.hfad.moviewishlist.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class MainViewModel(private val MovieRepository: MovieRepository) : ViewModel() {

    val movies: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()
    val searchmovie: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()
    val getMovieImages: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()

    fun getMovies() = viewModelScope.launch {
        movies.postValue(Resources.Loading())
        val response = MovieRepository.getMovie()
        movies.postValue(handleMovieResponse(response))
    }

    fun searchMovie(searchQuery: String) = viewModelScope.launch(Dispatchers.Main.immediate) {
        searchmovie.postValue(Resources.Loading())
        try {
            val response = MovieRepository.searchMovie(searchQuery)
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

    private fun handleSearchMovieResponse(response: Response<SearchMoviesResponse>): Resources<List<Movie>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resources.Success(it.results)
            }
        }
        return Resources.Error(response.message())
    }
}