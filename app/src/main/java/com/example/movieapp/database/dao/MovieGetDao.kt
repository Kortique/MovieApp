package com.example.movieapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.movieapp.database.entites.HistoryWithMovie
import com.example.movieapp.database.entites.Movie
import com.example.movieapp.database.entites.MovieWithNote
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieGetDao {

    @Query("SELECT id, viewingDate, movieId FROM History ORDER BY viewingDate DESC")
    fun getHistoryWithMovies(): List<HistoryWithMovie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovieById(id: Long): Movie

    @Query(
        """
        SELECT
            M.id,
            ifnull(F.id, 0) as isFavorite 
        FROM Movie as M 
        LEFT JOIN Favorite as F
            ON M.id = F.movieId
        WHERE M.id = :id"""
    )
    fun getMovieWithNoteById(id: Long): MovieWithNote

    @Query("SELECT movieId FROM Favorite")
    fun getAllFavoritesMoviesIds(): List<Long>

    @Query(
        """
        SELECT Movie.* FROM Movie
        INNER JOIN Favorite
            ON Movie.id = Favorite.movieId
        ORDER BY Favorite.addDate DESC
    """
    )
    fun getFavoritesMoviesFlow(): Flow<List<Movie>>

}