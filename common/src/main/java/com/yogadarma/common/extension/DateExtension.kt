package com.yogadarma.common.extension

import org.joda.time.format.DateTimeFormat

fun String.changeTimeFormat(fromFormat: String, targetFormat: String): String {
    if (this.isNotEmpty()) {
        val dateTime = DateTimeFormat
            .forPattern(fromFormat)
            .parseDateTime(this)

        return DateTimeFormat
            .forPattern(targetFormat)
            .print(dateTime.millis)
    }
    return "-"
}