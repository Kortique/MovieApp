package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.entities.AppState
import com.example.movieapp.entities.Movie
import com.example.movieapp.repositories.MoviesRepository

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _appState: MutableLiveData<AppState> = MutableLiveData(AppState.Loading)
    val appState: LiveData<AppState> = _appState

    fun fetchData() {
        Thread {
            var nowPlayingMovies = listOf<Movie>()
            moviesRepository.getNowPlayingMovies()
                .onSuccess { nowPlayingMovies = it }
                .onFailure {
                    _appState.postValue(AppState.Error(it))
                    return@Thread
                }

            var upcomingMovies = listOf<Movie>()
            moviesRepository.getUpcomingMovies()
                .onSuccess { upcomingMovies = it }
                .onFailure {
                    _appState.postValue(AppState.Error(it))
                    return@Thread
                }

            _appState.postValue(AppState.Success(nowPlayingMovies, upcomingMovies))
        }.start()
    }

}