package com.hfad.moviewishlist.repository

import androidx.lifecycle.asLiveData
import com.hfad.moviewishlist.api.RetrofitInstance
import com.hfad.moviewishlist.db.MovieDao
import com.hfad.moviewishlist.model.Movie
import com.hfad.moviewishlist.model.SearchMoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

open class DefaultMovieRepository(private val movieDao: MovieDao): MovieRepository {

    val allMovies: Flow<List<Movie>> = movieDao.getAllMovies()

    override fun readAllData(): Flow<List<Movie>> = movieDao.readAllData()

    override suspend fun getMovie(): Response<List<Movie>> {
         return RetrofitInstance.api.getMovie(1)
    }

    override suspend fun searchMovie(searchQuery: String) : Response<SearchMoviesResponse> {
        return RetrofitInstance.api.searchMovie(searchQuery)
    }

    override suspend fun upsertMovie(movie: Movie): Unit = movieDao.upsert(movie)

    override suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)

    override suspend fun deleteAllMovies() = movieDao.deleteAll()

    override fun searchDatabase(searchQuery: String): Flow<List<Movie>> = movieDao.searchDatabase(searchQuery)


}
