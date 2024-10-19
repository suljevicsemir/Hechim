package com.semirsuljevic.foundation.api.sdk.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.semirsuljevic.foundation.api.sdk.model.workout.Point
import com.semirsuljevic.foundation.api.sdk.model.workout.Workout
import com.semirsuljevic.foundation.api.sdk.model.workout.WorkoutEdge
import com.semirsuljevic.foundation.api.sdk.model.workout.WorkoutPoint
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkouts(): Flow<List<Workout>>
    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutAndEdges(id: Long): List<WorkoutEdge>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutAndPoints(id: Int): List<WorkoutPoint>

    @Insert
    suspend fun insertWorkout(workout: Workout): Long
    @Insert
    suspend fun insertPoint(point: Point): Long


}
