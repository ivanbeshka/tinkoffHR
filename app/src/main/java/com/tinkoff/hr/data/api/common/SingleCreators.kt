package com.tinkoff.hr.data.api.common

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Single

fun <T : Any> createSingleForQuery(
    taskBuilder: () -> Task<QuerySnapshot>,
    valueBuilder: (QuerySnapshot) -> T
): Single<T> {
    return Single.create { emitter ->
        taskBuilder()
            .addOnSuccessListener { querySnapshot ->
                emitter.onSuccess(valueBuilder(querySnapshot))
            }
            .addOnFailureListener { exception ->
                emitter.onError(exception)
            }
    }
}

