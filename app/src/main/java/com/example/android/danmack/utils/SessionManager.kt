package com.example.android.danmack.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.android.danmack.utils.Constants.SHARED_PREFS

/**
 * Shared Pref
 * */
object SessionManager {

    //Load details From Shared Preferences
    fun load(context: Context, prefType: String): String {
        val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(prefType, "").toString()
    }

    //Save details to Shared Preferences
    fun save(context: Context, prefType: String, prefValue: String) {
        val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(prefType, prefValue)
        editor.apply()
    }

    fun clearSharedPref(context: Context) {
        val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}