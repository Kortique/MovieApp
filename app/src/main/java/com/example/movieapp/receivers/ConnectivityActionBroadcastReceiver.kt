package com.example.movieapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.movieapp.R
import com.example.movieapp.utils.showToast

class ConnectivityActionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.showToast(R.string.connectivity_action)
    }
}