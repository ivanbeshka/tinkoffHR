package com.tinkoff.hr.data.api.common

import com.google.android.gms.tasks.Task
import io.reactivex.Completable

fun <T : Any> createCompletableForTask(
    taskBuilder: () -> Task<T>
): Completable {
    return Completable.create { emitter ->
        taskBuilder()
            .addOnSuccessListener {
                emitter.onComplete()
            }
            .addOnFailureListener { exception ->
                emitter.onError(exception)
            }
    }
}

