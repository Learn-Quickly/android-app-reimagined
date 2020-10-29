package com.pinkerton.lqrl.util

import android.content.SharedPreferences

class CredentialsSharedPreferencesManager(
    override var prefs: SharedPreferences
) : SharedPreferencesManager(prefs) {
    companion object {
        // region keys
        private const val KEY_TOKEN: String = "token"
        // endregion
    }

    fun getTokenOr(default: String = ""): String? {
        return super.getStringOr(KEY_TOKEN, default)
    }

    fun putToken(value: String) {
        return super.putString(KEY_TOKEN, value)
    }
}