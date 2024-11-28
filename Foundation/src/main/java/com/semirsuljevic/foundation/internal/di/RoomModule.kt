package com.semirsuljevic.foundation.internal.di

import android.content.Context
import androidx.room.Room
import com.semirsuljevic.foundation.api.sdk.room.WorkoutDatabase
import com.semirsuljevic.foundation.api.sdk.room.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        WorkoutDatabase::class.java,
        "workout-database.db"
    ).build()

    @Singleton
    @Provides
    fun provideWorkoutDao(trackerDatabase: WorkoutDatabase): WorkoutDao = trackerDatabase.workoutDao()


}
