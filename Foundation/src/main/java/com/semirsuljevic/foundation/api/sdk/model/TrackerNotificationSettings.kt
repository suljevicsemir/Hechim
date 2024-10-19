package com.semirsuljevic.foundation.api.sdk.model
import android.location.Location

data class TrackerNotificationSettings(
    var time: Int = 0,
    var distance: Double = 0.0,
    var paused: Boolean = false,
    var totalSteps: Int = -1,
    var workoutSteps: Int = 0,
    var initialPosition: Location = Location(""),
    val workoutId: Long = 0
)
