package com.example.movieapp.remote

import com.example.movieapp.remote.entites.MovieDetailsDTO
import com.example.movieapp.remote.entites.MoviesDTO
import com.example.movieapp.remote.entites.PersonDTO
import com.example.movieapp.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbService {

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") key: String = BuildConfig.MOVIEDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MoviesDTO>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") key: String = BuildConfig.MOVIEDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MoviesDTO>

    @GET("3/movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Long,
        @Query("api_key") key: String = BuildConfig.MOVIEDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") appendToResponse: String = "credits"
    ): Call<MovieDetailsDTO>

    @GET("3/person/{personId}")
    fun getPerson(
        @Path("personId") personId: Long,
        @Query("api_key") key: String = BuildConfig.MOVIEDB_API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<PersonDTO>
}