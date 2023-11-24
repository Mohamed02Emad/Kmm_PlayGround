package com.mo.kmp_hello_world.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getDateFromUTC(utcDate : String) : String{
    val date = Instant.parse(utcDate)
        .toLocalDateTime(TimeZone.currentSystemDefault())
    return "${date.month} ${date.dayOfMonth}, ${date.year}"
}