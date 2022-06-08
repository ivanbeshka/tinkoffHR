package com.tinkoff.hr.repository

import com.tinkoff.hr.data.api.EmployeesApi
import com.tinkoff.hr.domain.Employee
import com.tinkoff.hr.domain.converters.toDomainEmployees
import io.reactivex.Completable
import io.reactivex.Single

class EmployeesRepository(private val api: EmployeesApi) {

    fun getEmployees(): Single<List<Employee>> =
        api.getEmployees()
            .map {
                it.toDomainEmployees()
            }

    fun setStatus(employeeId: String, status: String): Completable =
        api.setStatus(employeeId, status)

    fun setBio(employeeId: String, bio: String): Completable =
        api.setBio(employeeId, bio)

}