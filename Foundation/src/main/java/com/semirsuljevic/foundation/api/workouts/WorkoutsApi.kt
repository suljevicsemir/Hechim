package com.semirsuljevic.foundation.api.workouts

import com.semirsuljevic.foundation.api.sdk.model.workout.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutsApi {
    fun getWorkouts(): Flow<List<Workout>>
    fun getWorkoutPoints(id: Long): Flow<Workout>
}
