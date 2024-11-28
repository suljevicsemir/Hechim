package com.semirsuljevic.foundation.internal.workouts

import com.semirsuljevic.foundation.api.sdk.model.workout.Workout
import com.semirsuljevic.foundation.api.sdk.room.dao.WorkoutDao
import com.semirsuljevic.foundation.api.workouts.WorkoutsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WorkoutsApiImpl @Inject constructor(
    private val workoutDao: WorkoutDao
): WorkoutsApi{
    override fun getWorkouts(): Flow<List<Workout>> = workoutDao.getWorkouts()

    override fun getWorkoutPoints(id: Long): Flow<Workout> = workoutDao.getWorkoutAndPoints(id)
}
