package com.example.movieapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.database.entites.MovieExtended
import com.example.movieapp.database.entites.Note
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class MovieDetailsViewModel(

    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _state: MutableLiveData<ScreenState<MovieExtended>> =
        MutableLiveData(ScreenState.Loading)
    val state: LiveData<ScreenState<MovieExtended>> = _state

    var messageToShare: String? = null

    fun fetchData(movieId: Long) {
        viewModelScope.launch {
            val item =
                withContext(Dispatchers.IO) { moviesRepository.getMovieExtendedById(movieId) }

            item?.let {
                messageToShare = "${item.movie.title} ${BuildConfig.URL_TO_SHARE}${item.movie.id}"
                _state.value = ScreenState.Success(item)
            }
        }
    }

    fun saveNote(movieId: Long, note: String) = viewModelScope.launch(Dispatchers.IO) {
        moviesRepository.saveNote(Note(movieId, note))
    }

    fun onFavoriteEvent(movieExtended: MovieExtended) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movieExtended.isFavorite) {
                moviesRepository.addMovieToFavorite(Favorite(movieId = movieExtended.movie.id))
            } else {
                moviesRepository.removeMovieFromFavorite(movieExtended.movie.id)
            }
        }
    }
}