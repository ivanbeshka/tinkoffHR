package com.tinkoff.hr.repository

import com.tinkoff.hr.api.EmployeesApi

class EmployeesRepository(private val api: EmployeesApi) {

    fun getEmployees() = api.getEmployees()
}