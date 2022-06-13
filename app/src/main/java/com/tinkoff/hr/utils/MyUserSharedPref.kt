package com.tinkoff.hr.utils

import android.content.Context

class MyUserSharedPref(context: Context) {
    private val userSharedPref =
        context.getSharedPreferences(USER_SHARED_PREF_NAME, Context.MODE_PRIVATE)

    var email: String?
        get() {
            return userSharedPref.getString(EMAIL_SHARED_PREF_KEY, null)
        }
        set(value) {
            if (value != null) {
                userSharedPref.edit().putString(EMAIL_SHARED_PREF_KEY, value).apply()
            }
        }

    var isLogin: Boolean
        get() {
            return userSharedPref.getBoolean(IS_LOGIN_SHARED_PREF_KEY, false)
        }
        set(value) {
            userSharedPref.edit().putBoolean(IS_LOGIN_SHARED_PREF_KEY, value).apply()
        }

    private companion object {
        private const val USER_SHARED_PREF_NAME = "my_user"
        private const val EMAIL_SHARED_PREF_KEY = "my_user_email"
        private const val IS_LOGIN_SHARED_PREF_KEY = "my_user_is_login"
    }
}