package com.clint.cars.core.extensions

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*

fun String.beautifyDateBasedOnYear(): String {
    val formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm").withLocale(Locale.getDefault())
    val date = formatter.parseDateTime(this)
//    Ideally current year should come from the server. For the time being, I'm storing it as static.
    val outputFormatter: DateTimeFormatter? = if (date.year == 2023) {
        DateTimeFormat.forPattern("dd MMM, HH:mm").withLocale(Locale.getDefault())
    } else {
        DateTimeFormat.forPattern("dd MMM yyyy, HH:mm").withLocale(Locale.getDefault())
    }
    return outputFormatter?.print(date).toString()
}