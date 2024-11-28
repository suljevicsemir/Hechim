package com.semirsuljevic.foundation.api.sdk.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.semirsuljevic.foundation.api.sdk.model.workout.Point
import com.semirsuljevic.foundation.api.sdk.model.workout.WorkoutInfo
import com.semirsuljevic.foundation.api.sdk.model.workout.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Transaction
    @Query("SELECT * FROM workout_info")
    fun getWorkouts(): Flow<List<Workout>>

    @Transaction
    @Query("SELECT * FROM workout_info WHERE id = :workoutId")
    fun getWorkoutAndPoints(workoutId: Long): Flow<Workout>

    @Insert
    suspend fun insertWorkoutInfo(workoutInfo: WorkoutInfo): Long

    @Insert
    suspend fun insertPoint(point: Point): Long
    @Query("UPDATE workout_info SET duration = :duration, distance = :distance WHERE id = :id")
    suspend fun updateDurationDistance(id: Long, duration: Int, distance: Double)








}
