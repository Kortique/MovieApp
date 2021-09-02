package com.example.movieapp.datasources

import com.example.movieapp.entities.Movie

interface DataSource {

    fun getNowPlayingMovies(): List<Movie>

    fun getUpcomingMovies(): List<Movie>

}