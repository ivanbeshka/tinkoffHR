package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkoff.hr.data.Employee
import java.text.SimpleDateFormat
import java.util.*

class EmployeesViewModel : ViewModel() {

    private val currentEmployee = MutableLiveData<Employee>()
    private val currentDate = SimpleDateFormat("dd/M/yyyy").format(Date())

    private val employee = Employee(
        "this_is_employee@gmail.com",
        "Иванов Александр Александрович",
        null,
        "Мне совершенно не важно, что думают обо мне люди. Все мои поступки, будь они плохие или хорошие, обдуманные или спонтанные и есть моя жизнь. Я рискую, прощаю, люблю, ненавижу, радуюсь, огорчаюсь, иногда забегаю вперед, иногда боюсь и не решаюсь. Жизнь дана мне один раз. И я живу так, как я хочу!",
        "Tinkoff HR",
        "Разработчик",
        currentDate,
        currentDate,
        "1024",
        "Работаю над проектом",
        "Спикер"
    )
    private val employees = MutableLiveData(
        listOf(
            employee,
            employee,
            employee,
            employee,
            employee,
            employee,
            employee,
            employee,
        )
    )

    fun getEmployees(): LiveData<List<Employee>> = employees

    fun setCurrentEmployee(employee: Employee) {
        currentEmployee.value = employee
    }

    fun getCurrentEmployee(): LiveData<Employee> = currentEmployee

}