package com.example.movieapp.repositories

import com.example.movieapp.datasources.DataSource
import com.example.movieapp.entities.Movie

class MoviesRepositoryImpl(private val dataSource: DataSource) : MoviesRepository {

    override fun getNowPlayingMovies(): List<Movie> = dataSource.getNowPlayingMovies()

    override fun getUpcomingMovies(): List<Movie> = dataSource.getUpcomingMovies()

}