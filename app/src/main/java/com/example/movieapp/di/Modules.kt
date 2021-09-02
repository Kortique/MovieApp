package com.example.movieapp.di

import com.example.movieapp.ui.favorites.FavoritesViewModel
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.ratings.RatingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel() }
    viewModel { FavoritesViewModel() }
    viewModel { RatingsViewModel() }
}