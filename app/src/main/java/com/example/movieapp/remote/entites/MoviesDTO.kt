package com.example.movieapp.remote.entites

import com.example.movieapp.entities.Movie

data class MoviesDTO(
    val page: Int,
    val results: List<Movie>
)