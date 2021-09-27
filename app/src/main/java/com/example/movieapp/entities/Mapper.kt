package com.example.movieapp.entities

typealias movieDb = com.example.movieapp.database.entites.Movie

val Movie.dbModel
    get() = movieDb(
        this.id,
        this.posterPath,
        this.adult,
        this.overview,
        this.releaseDate,
        this.originalTitle,
        this.originalLanguage,
        this.title,
        this.backdropPath,
        this.popularity,
        this.voteCount,
        this.video,
        this.voteAverage
    )