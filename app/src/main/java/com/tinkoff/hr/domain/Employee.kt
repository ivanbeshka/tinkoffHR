package com.tinkoff.hr.domain

data class Employee(
    val id: String,
    val email: String,
    val fio: String,
    val photoUrl: String? = null,
    val bio: String,
    val project: String,
    val companyPosition: String,
    val birthDate: String? = null,
    val employmentDate: String? = null,
    val tableNum: String,
    val status: String,
    val achievement: String
)
