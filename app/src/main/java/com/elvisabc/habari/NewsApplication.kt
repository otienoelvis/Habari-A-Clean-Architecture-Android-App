package com.elvisabc.habari

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Coming_Inside_onCreate")
    }

    companion object{
        const val TAG = "NewsApplication"
    }
}