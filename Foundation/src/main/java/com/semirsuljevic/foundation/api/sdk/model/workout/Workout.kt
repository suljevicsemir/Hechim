package com.semirsuljevic.foundation.api.sdk.model.workout

import androidx.room.Embedded
import androidx.room.Relation

data class Workout(
    @Embedded val workoutInfo: WorkoutInfo,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val list: List<Point>
)
