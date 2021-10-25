package com.hfad.moviewishlist.data.source

import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import com.hfad.moviewishlist.repository.MovieRepository
import com.hfad.moviewishlist.utils.Resources
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

class FakeDataSource(var movies: MutableList<Movie>? = mutableListOf()): MovieRepository {
    override fun readAllData(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovie(): Response<List<Movie>> {
        movies?.let { return Response.success(ArrayList(it)) }
        return Response.error(
            401,
            ResponseBody.create(
                MediaType.parse("application/json"),
                "{\"err\":\"Some server error\"}"
            )
        )
    }

    override suspend fun searchMovie(searchQuery: String): Response<SearchMoviesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertMovie(movie: Movie) {
        movies?.add(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllMovies() {
        movies?.clear()
    }

    override fun searchDatabase(searchQuery: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}