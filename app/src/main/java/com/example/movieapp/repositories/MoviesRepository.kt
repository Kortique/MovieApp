package com.example.movieapp.repositories

import com.example.movieapp.entities.Movie

interface MoviesRepository {

    fun getNowPlayingMovies(): Result<List<Movie>>

    fun getUpcomingMovies(): Result<List<Movie>>

}