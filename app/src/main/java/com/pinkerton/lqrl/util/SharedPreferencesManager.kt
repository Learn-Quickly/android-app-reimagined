package com.pinkerton.lqrl.util

import android.content.Context
import android.content.SharedPreferences
import com.pinkerton.lqrl.BuildConfig

abstract class SharedPreferencesManager(
    protected open var prefs: SharedPreferences,
) {
    companion object {
        // region keys
        private const val PREFS_KEY_CREDENTIALS: String = BuildConfig.APPLICATION_ID + ".credentials"
        // endregion

        // region Instances
        lateinit var CREDENTIALS: CredentialsSharedPreferencesManager
        // endregion

        fun init(context: Context) {
            CREDENTIALS = CredentialsSharedPreferencesManager(
                context.getSharedPreferences(PREFS_KEY_CREDENTIALS, Context.MODE_PRIVATE)
            )
        }
    }

    //region Wrapper
    //region Gets
    fun getBooleanOr(key: String, default: Boolean): Boolean? {
        return prefs.getBoolean(key, default)
    }

    fun getStringOr(key: String, default: String): String? {
        return prefs.getString(key, default)
    }

    fun getFloatOr(key: String, default: Float): Float? {
        return prefs.getFloat(key, default)
    }

    fun getIntOr(key: String, default: Int): Int? {
        return prefs.getInt(key, default)
    }

    fun getLongOr(key: String, default: Long): Long? {
        return prefs.getLong(key, default)
    }

    fun getStringSetOr(key: String, default: Set<String>): Set<String>? {
        return prefs.getStringSet(key, default)
    }
    //endregion
    //region Puts
    fun putBoolean(key: String, value: Boolean) {
        return prefs.edit().putBoolean(key, value).apply()
    }

    fun putString(key: String, value: String) {
        return prefs.edit().putString(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        return prefs.edit().putFloat(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        return prefs.edit().putInt(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        return prefs.edit().putLong(key, value).apply()
    }

    fun putStringSet(key: String, value: Set<String>) {
        return prefs.edit().putStringSet(key, value).apply()
    }
    //endregion
    //endregion
}