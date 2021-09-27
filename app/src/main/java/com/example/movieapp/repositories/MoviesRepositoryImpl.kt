package com.example.movieapp.repositories

import com.example.movieapp.database.dao.MovieGetDao
import com.example.movieapp.database.dao.MovieSetDao
import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.database.entites.History
import com.example.movieapp.database.entites.MovieWithNote
import com.example.movieapp.database.entites.Note
import com.example.movieapp.datasources.DataSource
import com.example.movieapp.entities.Movie
import com.example.movieapp.entities.coreModel
import com.example.movieapp.entities.dbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val dataSource: DataSource,
    private val movieSetDao: MovieSetDao,
    private val movieGetDao: MovieGetDao
) : MoviesRepository {
    override fun getNowPlayingMovies(): Result<List<Movie>> = dataSource.getNowPlayingMovies()
    override fun getUpcomingMovies(): Result<List<Movie>> = dataSource.getUpcomingMovies()
    override fun saveMovie(movie: Movie) = movieSetDao.insertMovie(movie.dbModel)
    override fun saveMovieToHistory(movieId: Long) {
        val history = History(movieId = movieId)
        movieSetDao.insertToHistory(history)
    }
    override fun getHistoryWithMovies() = movieGetDao.getHistoryWithMovies()
    override fun getMovieById(id: Long): Movie = movieGetDao.getMovieById(id).coreModel
    override fun saveNote(note: Note) = movieSetDao.insertNote(note)
    override fun getMovieWithNoteById(id: Long): MovieWithNote =
        movieGetDao.getMovieWithNoteById(id)

    override fun addMovieToFavorite(favorite: Favorite) = movieSetDao.insertToFavourite(favorite)

    override fun removeMovieFromFavorite(movieId: Long) =
        movieSetDao.deleteFromFavouriteByMovieId(movieId)

    override fun getAllFavoritesMoviesIds(): List<Long> = movieGetDao.getAllFavoritesMoviesIds()

    override fun getFavoritesMoviesFlow(): Flow<List<Movie>> =
        movieGetDao.getFavoritesMoviesFlow()
            .map { moviesDb ->
                moviesDb.map {
                    it.coreModel.apply { isFavorite = true }
                }
            }
}