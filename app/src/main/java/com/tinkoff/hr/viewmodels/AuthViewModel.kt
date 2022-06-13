package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.data.api.AuthApi
import com.tinkoff.hr.repository.AuthRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AuthViewModel : RxViewModel() {

    private val repository = AuthRepository(AuthApi())
    private val isSendAuthLink = MutableLiveData<ScreenState<Boolean>>()

    fun sendAuthLink(email: String): LiveData<ScreenState<Boolean>> {
        repository.sendAuthLink(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isSendAuthLink.value = LoadingScreenState() }
            .subscribeBy(
                onComplete = { isSendAuthLink.value = SuccessScreenState(true) },
                onError = { isSendAuthLink.value = ErrorScreenState(it) }
            )
            .disposeOnFinish()

        return isSendAuthLink
    }
}