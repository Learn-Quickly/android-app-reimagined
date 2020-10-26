package com.pinkerton.lqrl

import android.app.Application
import android.content.Intent
import android.util.Log
import com.pinkerton.lqrl.ui.LoginActivity
import com.pinkerton.lqrl.util.SharedPreferencesManager

class ApplicationLoader : Application() {

    companion object {
        const val TAG: String = "AppLoader"
    }

    // TODO: implement this if needed

    override fun onCreate() {
        super.onCreate()

        // Init singletons
        SharedPreferencesManager.init(applicationContext)
        SharedPreferencesManager.CREDENTIALS.getTokenOr("Nothing")?.let { Log.d(TAG, it) }
        SharedPreferencesManager.CREDENTIALS.putToken("New-Token")
    }
}