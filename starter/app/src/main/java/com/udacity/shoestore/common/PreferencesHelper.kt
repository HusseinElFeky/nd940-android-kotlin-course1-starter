package com.udacity.shoestore.common

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {

    private lateinit var preferences: SharedPreferences

    private const val PREFERENCES_NAME = "preferences"

    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = preferences.edit {
            it.putBoolean(KEY_IS_LOGGED_IN, value)
        }

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
