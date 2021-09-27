package com.example.movieapp.di

import com.example.movieapp.datasources.DataSource
import com.example.movieapp.datasources.RemoteDataSourceImpl
import com.example.movieapp.repositories.MoviesRepository
import com.example.movieapp.repositories.MoviesRepositoryImpl
import com.example.movieapp.ui.favorites.FavoritesViewModel
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.ratings.RatingsViewModel
import com.example.movieapp.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DataSource> { RemoteDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }

    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(get()) }
    single { NetworkModule.getMovieDbService(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { FavoritesViewModel() }
    viewModel { RatingsViewModel() }
    viewModel { SettingsViewModel() }
}