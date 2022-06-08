package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createCompletableForTask
import com.tinkoff.hr.data.api.common.createSingleForQuery
import com.tinkoff.hr.data.entities.EmployeePojo
import io.reactivex.Completable
import io.reactivex.Single

class EmployeesApi {
    private val employeesCollection = Firebase.firestore.collection(EMPLOYEES_PATH)

    fun getEmployees(): Single<List<EmployeePojo>> {
        return createSingleForQuery(
            taskBuilder = { employeesCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(EmployeePojo::class.java) }
        )
    }

    fun setStatus(employeeId: String, status: String): Completable {
        return createCompletableForTask {
            employeesCollection
                .document(employeeId)
                .update(EMPLOYEE_STATUS_FIELD, status)
        }
    }

    fun setBio(employeeId: String, bio: String): Completable {
        return createCompletableForTask {
            employeesCollection
                .document(employeeId)
                .update(EMPLOYEE_BIO_FIELD, bio)
        }
    }

    private companion object {
        private const val EMPLOYEES_PATH = "employees"
        private const val EMPLOYEE_STATUS_FIELD = "status"
        private const val EMPLOYEE_BIO_FIELD = "bio"
    }
}