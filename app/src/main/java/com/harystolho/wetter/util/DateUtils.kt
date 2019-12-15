package com.harystolho.wetter.util

import java.util.*

object DateUtils {

    /**
     * Converts an ISO date String to a Calendar object. The calendar is set to midnight
     *
     * @param iso YYYY-MM-DD
     *
     * @throws [IllegalArgumentException] if [iso] is not a valid ISO date
     * @throws [NumberFormatException] if a value can't be converted to a number
     */
    fun isoStringToCalendar(iso: String): Calendar {
        return Calendar.getInstance().apply {
            require(iso.count { it == '-' } == 2) { "Invalid ISO date | date: $iso" }

            val fields = iso.split("-")

            set(Calendar.YEAR, fields[0].toInt())
            set(Calendar.MONTH, fields[1].toInt() - 1) // January is 0
            set(Calendar.DAY_OF_MONTH, fields[2].toInt())

            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
    }


}