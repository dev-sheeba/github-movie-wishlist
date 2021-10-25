package com.hfad.moviewishlist.ui

import androidx.lifecycle.*
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import com.hfad.moviewishlist.repository.MovieRepository
import com.hfad.moviewishlist.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class MLainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movies: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()
    val searchMovie: MutableLiveData<Resources<List<Movie>>> = MutableLiveData()

    fun allMovies(): LiveData<List<Movie>> = movieRepository.readAllData().asLiveData()

    fun upsert(movie: Movie) = viewModelScope.launch {
        movieRepository.upsertMovie(movie)
    }

    fun getMovies() = viewModelScope.launch {
        movies.postValue(Resources.Loading())
        val response = movieRepository.getMovie()
        movies.postValue(handleMovieResponse(response))
    }

    fun searchMovie(searchQuery: String) = viewModelScope.launch(Dispatchers.Main.immediate) {
        searchMovie.postValue(Resources.Loading())
        try {
            val response = movieRepository.searchMovie(searchQuery)
            searchMovie.postValue(handleSearchMovieResponse(response))
        } catch (e: Exception) {
            Timber.e(e)
            searchMovie.postValue(Resources.Error(e.message))
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

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.upsertMovie(movie)
    }

    fun onMovieIsCheckedChanged(movie: Movie, isChecked: Boolean) = viewModelScope.launch {
        val newItem = movie.copy(isCompleted = isChecked)
            .also { it.movieId = movie.movieId }
        upsert(newItem)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }

    fun deleteAll() = viewModelScope.launch {
        movieRepository.deleteAllMovies()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Movie>> =
        movieRepository.searchDatabase(searchQuery).asLiveData()

}