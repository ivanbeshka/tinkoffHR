package com.tinkoff.hr.data.api.common

import android.util.Log
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
                Log.d(taskBuilder.toString(), "createCompletableForTask: $exception")
            }
    }
}

