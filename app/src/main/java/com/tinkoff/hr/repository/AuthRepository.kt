package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.AuthApi
import io.reactivex.Completable

class AuthRepository(private val api: AuthApi) {

    fun sendAuthLink(email: String): Completable {
        return api.sendAuthLink(email)
    }
}