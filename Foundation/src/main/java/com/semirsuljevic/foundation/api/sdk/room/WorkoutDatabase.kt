package com.semirsuljevic.foundation.api.sdk.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semirsuljevic.foundation.api.sdk.model.workout.Point
import com.semirsuljevic.foundation.api.sdk.model.workout.WorkoutInfo
import com.semirsuljevic.foundation.api.sdk.room.dao.WorkoutDao

@Database(
    entities = [
        WorkoutInfo::class,
        Point::class
    ],
    version = 1
)
@TypeConverters(WorkoutTypeConverters::class)
abstract class WorkoutDatabase: RoomDatabase(){
    abstract fun workoutDao(): WorkoutDao
}
