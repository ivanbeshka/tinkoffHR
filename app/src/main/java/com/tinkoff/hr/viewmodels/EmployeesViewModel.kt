package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.api.EmployeesApi
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.repository.EmployeesRepository
import io.reactivex.disposables.CompositeDisposable

class EmployeesViewModel : RxViewModel() {

    private val repository = EmployeesRepository(EmployeesApi())

    private val currentEmployee = MutableLiveData<Employee>()
    private val employees = MutableLiveData<List<Employee>>().apply {
        setData(
            repository.getEmployees(),
            compositeDisposable
        )
    }

    fun getEmployees(): LiveData<List<Employee>> = employees

    fun setCurrentEmployee(employee: Employee) {
        currentEmployee.value = employee
    }

    fun getCurrentEmployee(): LiveData<Employee> = currentEmployee
}