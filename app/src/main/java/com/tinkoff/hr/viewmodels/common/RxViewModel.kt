package com.tinkoff.hr.viewmodels.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun Disposable.disposeOnFinish() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}