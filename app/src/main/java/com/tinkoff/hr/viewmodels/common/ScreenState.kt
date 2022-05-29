package com.tinkoff.hr.viewmodels.common

sealed class ScreenState<T> {
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

class LoadingScreenState<T> : ScreenState<T>()
class SuccessScreenState<T>(val data: T) : ScreenState<T>()
class ErrorScreenState<T>(val throwable: Throwable) : ScreenState<T>() {
    private var isShowedError = false

    fun onError(doOnError: (Throwable) -> Unit) {
        if (!isShowedError) {
            doOnError(throwable)
            isShowedError = true
        }
    }
}