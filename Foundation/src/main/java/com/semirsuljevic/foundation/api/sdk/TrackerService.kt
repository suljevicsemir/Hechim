package com.semirsuljevic.foundation.api.sdk


import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.IBinder
import com.semirsuljevic.foundation.api.common.Dispatchers
import com.semirsuljevic.foundation.api.sdk.model.TrackerServiceAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrackerService: Service(), SensorEventListener {

    @Inject
    lateinit var manager: TrackerServiceManager
    @Inject
    lateinit var dispatchers: Dispatchers

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onSensorChanged(p0: SensorEvent?) = manager.onSensorChanged(p0)

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return when(TrackerServiceAction.fromIntentAction(intent)) {
            TrackerServiceAction.START -> {
                println("starting workout")
                manager.startWorkout(this)
                super.onStartCommand(intent, flags, startId)
            }
            TrackerServiceAction.STOP -> manager.stopWorkout(this)
            TrackerServiceAction.RESUME -> manager.resumeWorkout()
            TrackerServiceAction.PAUSE -> manager.pauseWorkout()
            TrackerServiceAction.UNKNOWN -> START_STICKY
        }
    }
}
