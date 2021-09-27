package com.example.movieapp.rest_entites

import com.example.movieapp.entities.Movie

data class NowPlayingMoviesDTO(
    val page: Int,
    val results: List<Movie>
)