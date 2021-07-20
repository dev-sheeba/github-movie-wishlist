package com.hfad.moviewishlist.ui

import android.app.Application
import com.hfad.moviewishlist.db.MovieRoomDatabase
import com.hfad.moviewishlist.repository.DefaultMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MovieApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MovieRoomDatabase.createDatabase(this, applicationScope) }

    val repository by lazy { DefaultMovieRepository(database.movieDao()) }
}
