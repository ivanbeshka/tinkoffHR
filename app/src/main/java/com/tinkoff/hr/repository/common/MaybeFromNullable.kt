package com.tinkoff.hr.repository.common

import io.reactivex.Maybe

fun <T: Any?> maybeFromNullable(value: T?): Maybe<T> {
    value?.let { return Maybe.just(it) }

    return Maybe.empty()
}