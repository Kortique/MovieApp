package com.example.movieapp.datasources

import com.example.movieapp.entities.Movie

interface DataSource {

    fun getNowPlayingMovies(): Result<List<Movie>>

    fun getUpcomingMovies(): Result<List<Movie>>

}