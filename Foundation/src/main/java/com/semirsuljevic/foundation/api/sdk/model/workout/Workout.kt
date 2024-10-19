package com.semirsuljevic.foundation.api.sdk.model.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "workouts"
)
@Serializable
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val distance: Double = 0.0,
    val duration: Int = 0
)
