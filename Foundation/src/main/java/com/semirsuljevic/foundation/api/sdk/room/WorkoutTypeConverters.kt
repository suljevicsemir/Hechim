package com.semirsuljevic.foundation.api.sdk.room

import androidx.room.TypeConverter
import com.semirsuljevic.foundation.api.common.serialiazers.HechimSerializers
import java.time.LocalDateTime
import javax.inject.Inject

class WorkoutTypeConverters {

    @Inject
    lateinit var serializer: HechimSerializers

    @TypeConverter
    fun toLocalDateTime(dateString: String) : LocalDateTime = LocalDateTime.parse(dateString)
    @TypeConverter
    fun dateToString(date: LocalDateTime): String = date.toString()
}
