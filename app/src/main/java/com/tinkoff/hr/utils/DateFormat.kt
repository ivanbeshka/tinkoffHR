package com.tinkoff.hr.utils

import java.text.SimpleDateFormat
import java.util.*

val dateFormatPattern = "dd/M/yyyy"

fun Date.format(): String {
    return SimpleDateFormat(dateFormatPattern).format(this)
}