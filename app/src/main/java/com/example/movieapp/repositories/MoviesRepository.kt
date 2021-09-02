package com.example.movieapp.repositories

import com.example.movieapp.entities.Movie

interface MoviesRepository {

    fun getNowPlayingMovies(): List<Movie>

    fun getUpcomingMovies(): List<Movie>

}