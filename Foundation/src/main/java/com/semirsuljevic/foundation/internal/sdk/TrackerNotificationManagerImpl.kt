package com.semirsuljevic.foundation.internal.sdk

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.hechimdemo.storage.R
import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerService
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants
import javax.inject.Inject

internal class TrackerNotificationManagerImpl @Inject constructor(
    private val context: Context
): TrackerNotificationManager{

    private lateinit var builder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager

    private lateinit var stopIntent: PendingIntent
    private lateinit var pauseIntent: PendingIntent
    private lateinit var resumeIntent: PendingIntent

    override fun onStartCommand(service: TrackerService) {
        notificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        initializeIntents(service)
        val notificationIntent = Intent(service, TrackerService::class.java)
        builder = NotificationCompat.Builder(service, TrackerServiceConstants.NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_workout_notification)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1))
            .addAction(R.drawable.ic_pause, context.getString(R.string.resource_label_pause), pauseIntent)
            .addAction(R.drawable.ic_stop, context.getString(R.string.resource_label_stop), stopIntent)
            .setColor(ContextCompat.getColor(service, R.color.orange_400))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(PendingIntent.getActivity(service, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE))
        service.startForeground(TrackerServiceConstants.NOTIFICATION_ID, builder.build())
    }

    private fun initializeIntents(service: Service) {
        pauseIntent = PendingIntent.getService(
            service, 0,
            Intent(service, TrackerService::class.java).apply { action = TrackerServiceConstants.PAUSE_WORKOUT },
            PendingIntent.FLAG_IMMUTABLE
        )
        stopIntent = PendingIntent.getService(
            service, 0,
            Intent(service, TrackerService::class.java).apply { action = TrackerServiceConstants.STOP_WORKOUT },
            PendingIntent.FLAG_IMMUTABLE
        )
        resumeIntent = PendingIntent.getService(
            service, 0,
            Intent(service, TrackerService::class.java).apply { action = TrackerServiceConstants.RESUME_WORKOUT },
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun resumeWorkout() {
        checkNotInitialized()
        builder.clearActions()
            .addAction(R.drawable.ic_pause, context.getString(R.string.resource_label_pause), pauseIntent)
            .addAction(R.drawable.ic_stop, context.getString(R.string.resource_label_stop), stopIntent)

        notificationManager.notify(TrackerServiceConstants.NOTIFICATION_ID, builder.build())
    }

    override fun pauseWorkout() {
        checkNotInitialized()
        builder.clearActions()
            .addAction(R.drawable.ic_start, context.getString(R.string.resource_label_resume), resumeIntent)
            .addAction(R.drawable.ic_stop, context.getString(R.string.resource_label_stop), stopIntent)

        notificationManager.notify(TrackerServiceConstants.NOTIFICATION_ID, builder.build())
    }

    override fun stopWorkout() {
        if(::notificationManager.isInitialized) {
            throw UninitializedPropertyAccessException("NotificationManager not initialized. Please ensure to call onStartCommand before starting a workout. ")
        }
        notificationManager.cancel(TrackerServiceConstants.NOTIFICATION_ID)
    }


    private fun checkNotInitialized() {
        if(!::builder.isInitialized) {
            throw UninitializedPropertyAccessException("NotificationCompat.Builder not initialized. Please ensure to call onStartCommand before starting a workout. ")
        }
    }
}
