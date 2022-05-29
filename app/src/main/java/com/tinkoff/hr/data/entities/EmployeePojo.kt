package com.tinkoff.hr.data.entities

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class EmployeePojo(
    @DocumentId
    var id: String = "",

    @set:PropertyName("email")
    @get:PropertyName("email")
    var email: String = "",

    @set:PropertyName("fio")
    @get:PropertyName("fio")
    var fio: String = "",

    @set:PropertyName("birth_date")
    @get:PropertyName("birth_date")
    var birthDate: Timestamp? = null,

    @set:PropertyName("employment_date")
    @get:PropertyName("employment_date")
    var employmentDate: Timestamp? = null,

    @set:PropertyName("photo_url")
    @get:PropertyName("photo_url")
    var photoUrl: String? = null,

    @set:PropertyName("bio")
    @get:PropertyName("bio")
    var bio: String = "",

    @set:PropertyName("project")
    @get:PropertyName("project")
    var project: String = "",

    @set:PropertyName("company_position")
    @get:PropertyName("company_position")
    var companyPosition: String = "",

    @set:PropertyName("status")
    @get:PropertyName("status")
    var status: String = "",

    @set:PropertyName("achievement")
    @get:PropertyName("achievement")
    var achievement: String = "",

    @set:PropertyName("table_num")
    @get:PropertyName("table_num")
    var tableNum: Int? = null,
)
