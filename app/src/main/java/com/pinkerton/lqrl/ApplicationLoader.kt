package com.pinkerton.lqrl

import android.app.Application
import android.util.Log
import com.pinkerton.lqrl.util.ApiUtils
import com.pinkerton.lqrl.util.SharedPreferencesManager
import com.pinkertone.apiwrapper.ApiWrapper
import com.pinkertone.apiwrapper.Resource
import com.pinkertone.apiwrapper.types.Token
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class ApplicationLoader : Application() {

    private lateinit var apiUtils: ApiUtils

    companion object {
        const val TAG: String = "AppLoader"
    }

    // TODO: implement this if needed

    override fun onCreate() {
        super.onCreate()

        // Init
        //// SharedPrefs
        SharedPreferencesManager.init(applicationContext)
        //// ApiUtils
        val language = Locale.getDefault().displayLanguage
        // In app apiUtils can now be created with empty constructor
        apiUtils = ApiUtils(ApiWrapper.getInstance(Constants.BASE_URL, language))
    }
}