package com.example.movieapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.BuildConfig
import com.example.movieapp.database.entites.Favorite
import com.example.movieapp.database.entites.MovieWithNote
import com.example.movieapp.database.entites.Note
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val uiScope = MainScope()
    private val _state: MutableLiveData<ScreenState<MovieWithNote>> =
        MutableLiveData(ScreenState.Loading)
    val state: LiveData<ScreenState<MovieWithNote>> = _state

    var messageToShare: String? = null

    fun fetchData(movieId: Long) {
        uiScope.launch {
            val item =
                withContext(Dispatchers.IO) { moviesRepository.getMovieWithNoteById(movieId) }

            messageToShare = "${item.movie.title} ${BuildConfig.URL_TO_SHARE}${item.movie.id}"

            _state.value = ScreenState.Success(item)
        }
    }
    fun saveNote(movieId: Long, note: String) = uiScope.launch(Dispatchers.IO) {
        moviesRepository.saveNote(Note(movieId, note))
    }
    fun onFavoriteEvent(movieWithNote: MovieWithNote) {
        uiScope.launch(Dispatchers.IO) {
            if (movieWithNote.isFavorite) {
                moviesRepository.addMovieToFavorite(Favorite(movieId = movieWithNote.id))
            } else {
                moviesRepository.removeMovieFromFavorite(movieWithNote.id)
            }
        }
    }
}