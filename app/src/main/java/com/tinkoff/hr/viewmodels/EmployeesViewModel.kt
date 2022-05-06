package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.api.EmployeesApi
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.repository.EmployeesRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EmployeesViewModel : RxViewModel() {

    private val repository = EmployeesRepository(EmployeesApi())

    private val currentEmployee = MutableLiveData<Employee>()
    private val employees = MutableLiveData<ScreenState<List<Employee>>>()

    fun getEmployees(): LiveData<ScreenState<List<Employee>>> {
        repository.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { employees.value = LoadingScreenState() }
            .subscribeBy(
                onSuccess = {
                    employees.value = SuccessScreenState(it)
                },
                onError = {
                    employees.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()
        return employees
    }

    fun setCurrentEmployee(employee: Employee) {
        currentEmployee.value = employee
    }

    fun getCurrentEmployee(): LiveData<Employee> = currentEmployee
}