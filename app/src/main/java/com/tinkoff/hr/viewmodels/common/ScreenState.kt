package com.tinkoff.hr.viewmodels.common

sealed class ScreenState<out T> {
    private val onSuccessEmpty: (T) -> Unit = {}
    private val onErrorEmpty: (Throwable) -> Unit = {}
    private val onLoadingEmpty: () -> Unit = {}

    fun on(
        success: (T) -> Unit = onSuccessEmpty,
        error: (Throwable) -> Unit = onErrorEmpty,
        loading: () -> Unit = onLoadingEmpty
    ) {
        when (this) {
            is SuccessScreenState -> success(data)
            is ErrorScreenState -> onError(error)
            is LoadingScreenState -> loading()
        }
    }
}

class LoadingScreenState : ScreenState<Nothing>()
class SuccessScreenState<T>(val data: T) : ScreenState<T>()
class ErrorScreenState(private val throwable: Throwable) : ScreenState<Nothing>() {
    private var isShowedError = false

    fun onError(doOnError: (Throwable) -> Unit) {
        if (!isShowedError) {
            doOnError(throwable)
            isShowedError = true
        }
    }
}