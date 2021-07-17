package com.hfad.moviewishlist.repository

import com.hfad.moviewishlist.api.RetrofitInstance
import com.hfad.moviewishlist.db.MovieDao
import com.hfad.moviewishlist.model.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieService(movieDao: MovieDao) {

    val allMovies: Flow<List<Movie>> =movieDao.getAllMovies()

    suspend fun getMovie(): Response<List<Movie>> {
         return RetrofitInstance.api.getMovie(1)
    }
    suspend fun searchMovie(searchQuery: String) : Response<List<Movie>> {
        return RetrofitInstance.api.searchMovie(searchQuery)
    }
}