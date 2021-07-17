package com.hfad.moviewishlist.db

import androidx.room.*
import com.hfad.moviewishlist.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie) : Long

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): Flow<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM movies_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<Movie>>
}