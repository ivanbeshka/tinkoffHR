package com.tinkoff.hr.data.api

import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createCompletableForTask
import io.reactivex.Completable

class AuthApi {

    private val actionCodeSettings = actionCodeSettings {
        // URL you want to redirect back to. The domain (www.example.com) for this
        // URL must be whitelisted in the Firebase Console.
        url = "https://tinkoffhr.com"
        handleCodeInApp = true
        setAndroidPackageName(
            "com.tinkoff.hr",
            false, /* installIfNotAvailable */
            "12" /* minimumVersion */)
    }

    fun sendAuthLink(email: String): Completable {
        return createCompletableForTask {
            Firebase.auth.sendSignInLinkToEmail(email, actionCodeSettings)
        }
    }
}