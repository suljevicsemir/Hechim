package com.semirsuljevic.foundation.api.sdk.room

import androidx.room.TypeConverter
import java.time.LocalDateTime

class WorkoutTypeConverters {
    @TypeConverter
    fun toLocalDateTime(dateString: String) : LocalDateTime = LocalDateTime.parse(dateString)
    @TypeConverter
    fun dateToString(date: LocalDateTime): String = date.toString()
}
