package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.entities.AppState
import com.example.movieapp.entities.Movie
import com.example.movieapp.repositories.MoviesRepository
import com.example.movieapp.wrappers.MainSharedPreferencesWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository,
    private val mainPreferences: MainSharedPreferencesWrapper
) : ViewModel() {

    private val uiScope = MainScope()

    private val _appState: MutableLiveData<AppState> = MutableLiveData(AppState.Loading)
    val appState: LiveData<AppState> = _appState

    private val isAdultContentEnabled: Boolean
        get() = mainPreferences.isAdultContentEnabled

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

            if (!isAdultContentEnabled) {
                nowPlayingMovies = nowPlayingMovies.filter { !it.adult }
                upcomingMovies = upcomingMovies.filter { !it.adult }
            }

            _appState.postValue(
                AppState.Success(
                    nowPlayingMovies.sortedByDescending { it.releaseDate },
                    upcomingMovies.sortedByDescending { it.releaseDate }
                )
            )
        }

        fun saveToHistory(movie: Movie) = uiScope.launch(Dispatchers.IO) {
            moviesRepository.saveMovie(movie)
            moviesRepository.saveMovieToHistory(movie.id)
        }

    }
}