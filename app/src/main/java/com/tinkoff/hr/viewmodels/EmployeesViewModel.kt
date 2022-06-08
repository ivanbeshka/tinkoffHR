package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tinkoff.hr.data.api.EmployeesApi
import com.tinkoff.hr.domain.Employee
import com.tinkoff.hr.repository.EmployeesRepository
import com.tinkoff.hr.viewmodels.common.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EmployeesViewModel : RxViewModel() {

    private val repository = EmployeesRepository(EmployeesApi())

    private val currentEmployee = MutableLiveData<Employee>()
    private val employees = MutableLiveData<ScreenState<List<Employee>>>()
    private val isSetBio = MutableLiveData<ScreenState<Boolean>>()
    private val isSetStatus = MutableLiveData<ScreenState<Boolean>>()

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

    fun setStatus(status: String): LiveData<ScreenState<Boolean>> {
        //todo
        repository.setStatus("IWPiQDppRkcpJ3n2OUl8", status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isSetStatus.value = LoadingScreenState() }
            .subscribeBy(
                onComplete = {
                    isSetStatus.value = SuccessScreenState(true)
                },
                onError = {
                    isSetStatus.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()
        return isSetStatus
    }

    fun setBio(bio: String): LiveData<ScreenState<Boolean>> {
        //todo
        repository.setBio("IWPiQDppRkcpJ3n2OUl8", bio)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isSetBio.value = LoadingScreenState() }
            .subscribeBy(
                onComplete = {
                    isSetBio.value = SuccessScreenState(true)
                },
                onError = {
                    isSetBio.value = ErrorScreenState(it)
                }
            )
            .disposeOnFinish()
        return isSetBio
    }

    fun setCurrentEmployee(employee: Employee) {
        currentEmployee.value = employee
    }

    fun getCurrentEmployee(): LiveData<Employee> = currentEmployee
}