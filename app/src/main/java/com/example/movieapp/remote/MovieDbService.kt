package com.example.movieapp.remote

import com.example.movieapp.remote.entites.MoviesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MoviesDTO>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MoviesDTO>

}