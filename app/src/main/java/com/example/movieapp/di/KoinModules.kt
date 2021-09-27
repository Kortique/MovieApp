package com.example.movieapp.di

import com.example.movieapp.database.DatabaseModule
import com.example.movieapp.datasources.DataSource
import com.example.movieapp.datasources.RemoteDataSourceImpl
import com.example.movieapp.repositories.MoviesRepository
import com.example.movieapp.repositories.MoviesRepositoryImpl
import com.example.movieapp.ui.details.MovieDetailsViewModel
import com.example.movieapp.ui.favorites.FavoritesViewModel
import com.example.movieapp.ui.history.HistoryViewModel
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.ratings.RatingsViewModel
import com.example.movieapp.ui.settings.SettingsViewModel
import com.example.movieapp.wrappers.MainSharedPreferencesWrapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataSource> { RemoteDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get()) }

    // Network
    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(get()) }
    single { NetworkModule.getMovieDbService(get()) }

    // SharedPreferences
    single { MainSharedPreferencesWrapper(get()) }

    // Database
    single { DatabaseModule.getAppDatabase(get()) }
    single { DatabaseModule.getMovieGetDao(get()) }
    single { DatabaseModule.getMovieSetDao(get()) }

    // view models
    viewModel { HomeViewModel(get(), get()) }
    viewModel { FavoritesViewModel() }
    viewModel { RatingsViewModel() }
    viewModel { SettingsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}