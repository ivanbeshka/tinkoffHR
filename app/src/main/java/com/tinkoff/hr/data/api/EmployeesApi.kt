package com.tinkoff.hr.data.api

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.data.entities.EmployeePojo
import io.reactivex.Single

class EmployeesApi {
    private val employeesCollection = Firebase.firestore.collection(EMPLOYEES_PATH)

    fun getEmployees(): Single<List<EmployeePojo>> {
        return Single.create { emitter ->
            employeesCollection.get()
                .addOnSuccessListener {
                    val employees = mutableListOf<EmployeePojo>()
                    it.forEach { document ->
                        val employee = document.toObject(EmployeePojo::class.java)
                        employees.add(employee)
                    }
                    emitter.onSuccess(employees)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    private companion object {
        private const val EMPLOYEES_PATH = "employees"
    }
}