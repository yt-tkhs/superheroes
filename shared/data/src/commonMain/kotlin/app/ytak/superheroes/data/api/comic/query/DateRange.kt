package app.ytak.superheroes.data.api.comic.query

import com.soywiz.klock.Date
import com.soywiz.klock.DateFormat
import com.soywiz.klock.format

data class DateRange(
    val from: Date,
    val to: Date
) {

    val code get() = "${DateFormat.FORMAT_DATE.format(from)},${DateFormat.FORMAT_DATE.format(to)}"
}
