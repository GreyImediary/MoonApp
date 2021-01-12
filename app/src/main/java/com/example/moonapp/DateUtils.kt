package com.example.moonapp

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val FULL_DAY_FORMAT = "dd MMMM yyyy"
    private const val TIME_DATE_FORMAT = "HH:mm"

    fun getFullDay(): String {
        val date = Calendar.getInstance().time

        return SimpleDateFormat(FULL_DAY_FORMAT, Locale("ru")).format(date)
    }

    fun getTime(): String {
        val date = Calendar.getInstance().time

        return SimpleDateFormat(TIME_DATE_FORMAT, Locale("ru")).format(date)
    }
}