package com.semirsuljevic.foundation.api.sdk.model.workout

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.semirsuljevic.foundation.api.sdk.config.LocalDateTimeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Parcelize
@Serializable
@Entity(tableName = "points")
data class Point(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val speed: Float? = null,
    val workoutId: Long,
    @Serializable(with = LocalDateTimeSerializer::class)
    val time: LocalDateTime
): Parcelable {
    fun toLatLng(): LatLng = LatLng(latitude ?: 0.0, longitude ?: 0.0)
}
