package com.tinkoff.hr.data

import java.util.*

data class Employee(
    val email: String,
    val fio: String,
    val photoUrl: String? = null,
    val bio: String? = null,
    val project: String? = null,
    val companyPosition: String? = null,
    val birthDate: String? = null,
    val employmentDate: String? = null,
    val tableNum: String? = null,
    val status: String? = null,
    val achievement: String? = null
    )
