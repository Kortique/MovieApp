package com.example.movieapp.repositories

import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.database.entites.HistoryWithMovie
import com.example.movieapp.entities.Movie
import com.example.movieapp.database.entites.MovieWithNote
import com.example.movieapp.database.entites.Note
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getNowPlayingMovies(): Result<List<Movie>>

    fun getUpcomingMovies(): Result<List<Movie>>

    fun saveMovie(movie: Movie)

    fun saveMovieToHistory(movieId: Long)

    fun getHistoryWithMovies(): List<HistoryWithMovie>

    fun getMovieById(id: Long): Movie

    fun saveNote(note: Note)

    fun getMovieWithNoteById(id: Long): MovieWithNote

    fun addMovieToFavorite(favorite: Favorite)

    fun removeMovieFromFavorite(movieId: Long)

    fun getAllFavoritesMoviesIds(): List<Long>

    fun getFavoritesMoviesFlow(): Flow<List<Movie>>
}