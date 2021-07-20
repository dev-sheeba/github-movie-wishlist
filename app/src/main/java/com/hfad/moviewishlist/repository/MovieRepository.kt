package com.hfad.moviewishlist.repository

import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovie(): Response<List<Movie>>

    suspend fun searchMovie(searchQuery: String) : Response<SearchMoviesResponse>

}