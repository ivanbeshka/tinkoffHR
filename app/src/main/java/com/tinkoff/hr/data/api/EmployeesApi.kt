package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.api.common.createSingleForTask
import com.tinkoff.hr.data.entities.EmployeePojo
import io.reactivex.Single

class EmployeesApi {
    private val employeesCollection = Firebase.firestore.collection(EMPLOYEES_PATH)

    fun getEmployees(): Single<List<EmployeePojo>> {
        return createSingleForTask(
            taskBuilder = { employeesCollection.get() },
            valueBuilder = { querySnapshot -> querySnapshot.toObjects(EmployeePojo::class.java) }
        )
    }

    private companion object {
        private const val EMPLOYEES_PATH = "employees"
    }
}