package com.semirsuljevic.foundation.api.sdk.model.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "workout_info")
@Serializable
data class WorkoutInfo(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val distance: Double = 0.0,
    val duration: Int = 0
)
