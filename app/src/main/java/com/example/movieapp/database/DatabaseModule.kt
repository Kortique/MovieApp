package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import com.example.movieapp.database.dao.MovieGetDao
import com.example.movieapp.database.dao.MovieSetDao

object DatabaseModule {
    private const val DATABASE_NAME = "movies_database.db"

    fun getAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    fun getMovieGetDao(appDatabase: AppDatabase): MovieGetDao = appDatabase.getMovieGetDao()

    fun getMovieSetDao(appDatabase: AppDatabase): MovieSetDao = appDatabase.getMovieSetDao()
}