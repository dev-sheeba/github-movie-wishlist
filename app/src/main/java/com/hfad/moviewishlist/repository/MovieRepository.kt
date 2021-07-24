package com.hfad.moviewishlist.repository

import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun readAllData(): Flow<List<Movie>>

    suspend fun getMovie(): Response<List<Movie>>

    suspend fun searchMovie(searchQuery: String) : Response<SearchMoviesResponse>

    suspend fun upsertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun deleteAllMovies()

    fun searchDatabase(searchQuery: String): Flow<List<Movie>>


}