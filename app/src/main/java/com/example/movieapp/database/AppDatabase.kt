package com.example.movieapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.database.converters.DateConverter
import com.example.movieapp.database.dao.MovieGetDao
import com.example.movieapp.database.dao.MovieSetDao
import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.database.entites.History
import com.example.movieapp.database.entites.Movie
import com.example.movieapp.database.entites.Note

@Database(
    entities = [
        Movie::class, History::class, Note::class, Favorite::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieGetDao(): MovieGetDao
    abstract fun getMovieSetDao(): MovieSetDao
}