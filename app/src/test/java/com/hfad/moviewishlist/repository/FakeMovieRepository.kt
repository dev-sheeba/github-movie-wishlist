package com.hfad.moviewishlist.repository

import com.hfad.moviewishlist.api.RetrofitInstance
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import com.hfad.moviewishlist.utils.Resources
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

//class FakeMovieRepository : MovieRepository {
//
//    private var shouldReturnNetworkError = false
//
//    fun setShouldReturnNetworkError(value: Boolean) {
//        shouldReturnNetworkError = value
//    }
//
//    override suspend fun getMovie(): Response<List<Movie>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun searchMovie(searchQuery: String): Response<SearchMoviesResponse> {
//        return if (shouldReturnNetworkError) {
//            Response.error(
//                401,
//                ResponseBody.create(
//                    MediaType.parse("application/json"),
//                    "{\"err\":\"Some server error\"}"
//                )
//            )
//        } else {
////            Response.success(listOf(Movie(1, "", "", "", "", 9.0)))
//            Response.success(SearchMoviesResponse(listOf()))
//        }
//    }
//
//}