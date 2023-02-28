package com.example.lawyersapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import com.google.android.gms.common.api.GoogleApiClient
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var prefs : SharedPreferences
        lateinit var mGoogleApiClient : GoogleApiClient
        var mGoogleApiInit = false
            get() = ::mGoogleApiClient.isInitialized

    }

    override fun onCreate() {
        super.onCreate()
        prefs = getSharedPreferences("AppShared", Context.MODE_PRIVATE)
    }
}