package com.mlbench.tesla.utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mlbench.probau.utils.SharePref
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class TeslaWeather:Application() {
    var sharePref: SharePref? = null
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        initialize()
    }

    private fun initialize() {
        SharePref.init(this)
        sharePref = SharePref.getInstance()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}