package com.example.movieapp.repositories

import com.example.movieapp.database.dao.MovieGetDao
import com.example.movieapp.database.dao.MovieSetDao
import com.example.movieapp.database.entites.History
import com.example.movieapp.datasources.DataSource
import com.example.movieapp.entities.Movie
import com.example.movieapp.entities.dbModel

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

}