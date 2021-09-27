package com.example.movieapp.repositories

import com.example.movieapp.database.entites.HistoryWithMovie
import com.example.movieapp.entities.Movie

interface MoviesRepository {
    fun getNowPlayingMovies(): Result<List<Movie>>

    fun getUpcomingMovies(): Result<List<Movie>>

    fun saveMovie(movie: Movie)

    fun saveMovieToHistory(movieId: Long)

    fun getHistoryWithMovies(): List<HistoryWithMovie>
}