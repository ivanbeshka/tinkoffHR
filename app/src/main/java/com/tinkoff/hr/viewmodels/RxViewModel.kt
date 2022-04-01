package com.tinkoff.hr.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class RxViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }
}