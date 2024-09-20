package com.semirsuljevic.foundation.api.sdk

import android.app.Service
import android.hardware.SensorEventListener
import android.location.Location
import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings
import kotlinx.coroutines.flow.Flow

interface TrackerServiceManager {

    fun formatDurationTime(): String
    val location: Flow<Location>
    val settings: Flow<TrackerNotificationSettings>

    fun startWorkout(service: TrackerService)
    fun stopWorkout(service: TrackerService): Int
    fun resumeWorkout(): Int
    fun pauseWorkout(): Int
}
