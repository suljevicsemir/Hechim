package com.semirsuljevic.foundation.api.sdk

import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings

interface TrackerNotificationManager {

    fun onStartCommand(service: TrackerService)
    fun resumeWorkout()
    fun pauseWorkout()
    fun stopWorkout()

    fun updateNotification(settings: TrackerNotificationSettings)

    fun createChannel()
}
