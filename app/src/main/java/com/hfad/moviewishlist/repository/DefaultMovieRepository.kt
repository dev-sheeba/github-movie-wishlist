package com.hfad.moviewishlist.repository

import com.hfad.moviewishlist.api.RetrofitInstance
import com.hfad.moviewishlist.db.MovieDao
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit

class DefaultMovieRepository(movieDao: MovieDao): MovieRepository {

    val allMovies: Flow<List<Movie>> =movieDao.getAllMovies()

    override suspend fun getMovie(): Response<List<Movie>> {
         return RetrofitInstance.api.getMovie(1)
    }

    override suspend fun searchMovie(searchQuery: String) : Response<SearchMoviesResponse> {
        return RetrofitInstance.api.searchMovie(searchQuery)
    }

}
