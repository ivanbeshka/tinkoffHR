package com.tinkoff.hr.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class TokenUtil(context: Context) {

    companion object {
        const val SHARED_PREF_KEY = "SHARED_PREF_TOKEN"
        const val TOKEN_KEY = "token"
    }

    val preferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    var token: String?
        get() = preferences.getString(TOKEN_KEY, null)
        set(value) = preferences.edit {
            this.putString(TOKEN_KEY, value)
            this.apply()
        }
}