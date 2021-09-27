package com.example.movieapp.datasources

import com.google.gson.Gson
import com.example.movieapp.BuildConfig
import com.example.movieapp.entities.Movie
import com.example.movieapp.rest_entites.NowPlayingMoviesDTO
import com.example.movieapp.utils.getLines
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RemoteDataSourceImpl : DataSource {

    override fun getNowPlayingMovies(): Result<List<Movie>> {
        try {
            val url =
                URL("${BuildConfig.MOVIEDB_BASE_URL}/3/movie/now_playing?api_key=" +
                        "${BuildConfig.MOVIEDB_API_KEY}&language=en-US&page=1")

            lateinit var urlConnection: HttpsURLConnection

            return try {
                urlConnection = url.openConnection() as HttpsURLConnection

                if (urlConnection.responseCode != HttpsURLConnection.HTTP_OK) {
                    return Result.failure(Exception("Response code: ${urlConnection.responseCode}. " +
                            "Response message: ${urlConnection.responseMessage}"))
                }

                val lines = urlConnection.getLines()
                val nowPlayingMoviesDTO = Gson().fromJson(lines, NowPlayingMoviesDTO::class.java)

                Result.success(nowPlayingMoviesDTO.results)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            } finally {
                urlConnection.disconnect()
            }

        } catch (e: MalformedURLException) {
            return Result.failure(e)
        }

    }

    override fun getUpcomingMovies(): Result<List<Movie>> {
        try {
            val url =
                URL("${BuildConfig.MOVIEDB_BASE_URL}/3/movie/upcoming?api_key=" +
                        "${BuildConfig.MOVIEDB_API_KEY}&language=en-US&page=1")

            lateinit var urlConnection: HttpsURLConnection

            return try {
                urlConnection = url.openConnection() as HttpsURLConnection

                if (urlConnection.responseCode != HttpsURLConnection.HTTP_OK) {
                    return Result.failure(Exception("Response code: ${urlConnection.responseCode}." +
                            " Response message: ${urlConnection.responseMessage}"))
                }

                val lines = urlConnection.getLines()
                val nowPlayingMoviesDTO = Gson().fromJson(lines, NowPlayingMoviesDTO::class.java)

                Result.success(nowPlayingMoviesDTO.results)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            return Result.failure(e)
        }
    }

}