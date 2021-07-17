package com.hfad.moviewishlist.api

import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie")
    suspend fun getMovie(
        @Query("movie_id")
        movieId: Int,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<List<Movie>>

    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query("query")
        searchQuery: String,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<List<Movie>>
}