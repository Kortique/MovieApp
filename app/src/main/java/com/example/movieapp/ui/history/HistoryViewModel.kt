package com.example.movieapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.entites.HistoryWithMovie
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.repositories.MoviesRepository
import kotlinx.coroutines.*

class HistoryViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state: MutableLiveData<ScreenState<List<HistoryWithMovie>>> =
        MutableLiveData(ScreenState.Loading)

    val state: LiveData<ScreenState<List<HistoryWithMovie>>> = _state

    private lateinit var historyWithMovies: List<HistoryWithMovie>

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            historyWithMovies = withContext(Dispatchers.IO) {
                moviesRepository.getHistoryWithMovies()
            }

            _state.value = ScreenState.Success(historyWithMovies)
        }
    }

    fun searchByQuery(query: String?) {
        searchJob?.cancel()

        if (query.isNullOrBlank()) {
            _state.value = ScreenState.Success(historyWithMovies)
            return
        }

        _state.value = ScreenState.Loading

        searchJob = viewModelScope.launch {
            delay(500)
            val searchResult = historyWithMovies.filter { historyWithMovie ->
                historyWithMovie.movie.title.contains(query, ignoreCase = true) ||
                        historyWithMovie.movie.overview.contains(query, ignoreCase = true)
            }

            _state.value = ScreenState.Success(searchResult)
        }
    }

}