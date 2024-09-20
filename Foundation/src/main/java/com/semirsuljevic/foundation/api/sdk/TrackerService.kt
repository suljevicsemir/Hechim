package com.semirsuljevic.foundation.api.sdk

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.hechimdemo.storage.R
import com.semirsuljevic.foundation.api.common.Dispatchers
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants
import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings
import com.semirsuljevic.foundation.api.sdk.model.TrackerServiceAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TrackerService: Service(), SensorEventListener {

    @Inject
    lateinit var manager: TrackerServiceManager
    @Inject
    lateinit var dispatchers: Dispatchers

    private var builder: NotificationCompat.Builder? = null
    private lateinit var notificationManager: NotificationManager


    override fun onBind(p0: Intent?): IBinder? = null

    override fun onSensorChanged(p0: SensorEvent?) {
        //pozovi manager, registruj settings
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return when(TrackerServiceAction.fromIntentAction(intent)) {
            TrackerServiceAction.START -> {
                CoroutineScope(dispatchers.io).launch {
                    launch {
                        manager.settings.collectLatest { updateNotification(it) }
                    }
                }
                manager.startWorkout(this)
                super.onStartCommand(intent, flags, startId)
            }
            TrackerServiceAction.STOP -> manager.stopWorkout(this)
            TrackerServiceAction.RESUME -> manager.resumeWorkout()
            TrackerServiceAction.PAUSE -> manager.pauseWorkout()
            TrackerServiceAction.UNKNOWN -> START_STICKY
        }
    }

    private fun updateNotification(settings: TrackerNotificationSettings) {
        if(builder == null) {
            return
        }
        println("updating notification, paused: ${settings.paused}")
        val titleContent = "%.2f".format(settings.distance / 1000) + " km"
        builder!!.setContentTitle(
            HtmlCompat.fromHtml(
            "<font color=\"" +
                ContextCompat.getColor(this, R.color.orange_400) +
                "\">" + titleContent + "</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY))

        builder!!.setContentText(manager.formatDurationTime() + " Steps ${settings.workoutSteps - settings.totalSteps}")

        notificationManager.notify(1, builder!!.build())
    }
}
