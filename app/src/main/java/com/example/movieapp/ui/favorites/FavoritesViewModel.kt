package com.example.movieapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.entities.Movie
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val uiScope = MainScope()

    private val _state: MutableLiveData<ScreenState<List<Movie>>> =
        MutableLiveData(ScreenState.Loading)
    val state: LiveData<ScreenState<List<Movie>>> = _state

    init {
        uiScope.launch {
            val moviesFlow = withContext(Dispatchers.IO) {
                moviesRepository.getFavoritesMoviesFlow()
            }

            moviesFlow.collectLatest {
                _state.value = ScreenState.Success(it)
            }
        }
    }

    fun onFavoriteEvent(movieId: Long, isFavorite: Boolean) {
        uiScope.launch(Dispatchers.IO) {
            if (isFavorite) {
                moviesRepository.addMovieToFavorite(Favorite(movieId = movieId))
            } else {
                moviesRepository.removeMovieFromFavorite(movieId)
            }
        }
    }
}