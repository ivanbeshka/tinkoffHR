package com.tinkoff.hr.data.api.common

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Completable

fun createCompletableForTask(
    taskBuilder: () -> Task<DocumentReference>
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