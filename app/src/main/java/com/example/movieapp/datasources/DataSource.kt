package com.example.movieapp.datasources

import com.example.movieapp.entities.Movie
import com.example.movieapp.remote.entites.MovieDetailsDTO
import com.example.movieapp.remote.entites.PersonDTO

interface DataSource {

    fun getNowPlayingMovies(): Result<List<Movie>>

    fun getUpcomingMovies(): Result<List<Movie>>

    fun getMovieDetails(movieId: Long): Result<MovieDetailsDTO?>

    fun getPerson(personId: Long): Result<PersonDTO?>

}