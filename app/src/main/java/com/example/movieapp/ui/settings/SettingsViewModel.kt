package com.example.movieapp.ui.settings

import androidx.lifecycle.ViewModel
import com.example.movieapp.wrappers.MainSharedPreferencesWrapper

class SettingsViewModel(
    private val mainPreferences: MainSharedPreferencesWrapper
) : ViewModel() {

    var isAdultContentEnabled: Boolean
        get() = mainPreferences.isAdultContentEnabled
        set(value) {
            mainPreferences.isAdultContentEnabled = value
        }

}