package com.example.movieapp.di

import com.example.movieapp.datasources.DataSource
import com.example.movieapp.datasources.DummyDataSourceImpl
import com.example.movieapp.repositories.MoviesRepository
import com.example.movieapp.repositories.MoviesRepositoryImpl
import com.example.movieapp.ui.favorites.FavoritesViewModel
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.ratings.RatingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DataSource> { DummyDataSourceImpl() }

    single<MoviesRepository> { MoviesRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { FavoritesViewModel() }
    viewModel { RatingsViewModel() }
}