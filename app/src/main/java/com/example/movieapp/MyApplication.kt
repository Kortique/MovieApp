package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.movieapp.helpers.NotificationHelper

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
        NotificationHelper(this).createChannels()
    }
}