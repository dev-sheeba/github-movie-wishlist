package com.hfad.moviewishlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.moviewishlist.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieRoomDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    private class MovieDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.movieDao())
                }
            }
        }

        suspend fun populateDatabase(movieDao: MovieDao) {

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun createDatabase(context: Context, scope: CoroutineScope): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                )
                    .addCallback(MovieDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }

        }


    }

}