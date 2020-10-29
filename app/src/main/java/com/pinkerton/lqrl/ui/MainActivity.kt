package com.pinkerton.lqrl.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pinkerton.lqrl.ApplicationLoader
import com.pinkerton.lqrl.R
import com.pinkerton.lqrl.util.ApiUtils
import com.pinkerton.lqrl.util.SharedPreferencesManager
import com.pinkertone.apiwrapper.ApiWrapper
import com.pinkertone.apiwrapper.Status
import com.pinkertone.apiwrapper.types.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val apiUtils: ApiUtils = ApiUtils(ApiWrapper.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isLoggedIn()) {
            Log.d(ApplicationLoader.TAG, "User is NOT logged in!")
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            this.finish()
            return
        } else {
            Log.d(ApplicationLoader.TAG, "User is logged in. Starting Main Activity!")
        }
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun isLoggedIn(): Boolean {
        // Test token
        val tokenStr = SharedPreferencesManager.CREDENTIALS.getTokenOr()

        var status: Boolean = false;
        if (tokenStr?.isNotEmpty() == true) {
            Log.d(ApplicationLoader.TAG, String.format("Saved token is: %s", tokenStr))
            val token = Token(tokenStr)

            // TODO: async API
            runBlocking {
                status = apiUtils.checkToken(token).status == Status.SUCCESS
            }
        } else {
            Log.d(ApplicationLoader.TAG, "No saved token found!")
        }

        return status
    }

}