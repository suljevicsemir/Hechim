package com.semirsuljevic.foundation.api.sdk.model.workout

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutPoint(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val list: List<Point>
)
