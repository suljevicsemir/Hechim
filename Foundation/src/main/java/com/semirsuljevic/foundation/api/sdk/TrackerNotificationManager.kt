package com.semirsuljevic.foundation.api.sdk

interface TrackerNotificationManager {

    fun onStartCommand(service: TrackerService)
    fun resumeWorkout()
    fun pauseWorkout()
    fun stopWorkout()
}
