package com.example.movieapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.entites.HistoryWithMovie
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class HistoryViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val uiScope = MainScope()

    private val _state: MutableLiveData<ScreenState<List<HistoryWithMovie>>> =
        MutableLiveData(ScreenState.Loading)
    val state: LiveData<ScreenState<List<HistoryWithMovie>>> = _state

    init {
        uiScope.launch {
            val items = withContext(Dispatchers.IO) { moviesRepository.getHistoryWithMovies() }
            _state.value = ScreenState.Success(items)
        }
    }
}