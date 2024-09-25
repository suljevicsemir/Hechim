package com.semirsuljevic.foundation.internal.sdk

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.hechimdemo.storage.R
import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerService
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants
import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

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
        builder = NotificationCompat.Builder(service, TrackerServiceConstants.NOTIFICATION_CHANNEL_ID)
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
        if(!::notificationManager.isInitialized) {
            throw UninitializedPropertyAccessException("NotificationManager not initialized. Please ensure to call onStartCommand before starting a workout. ")
        }
        notificationManager.cancel(TrackerServiceConstants.NOTIFICATION_ID)
    }

    override fun updateNotification(settings: TrackerNotificationSettings) {
        val titleContent = "%.2f".format(settings.distance / 1000) + " km"
        builder.setContentTitle(
            HtmlCompat.fromHtml(
                "<font color=\"" +
                    ContextCompat.getColor(context, R.color.orange_400) +
                    "\">" + titleContent + "</font>",
                HtmlCompat.FROM_HTML_MODE_LEGACY))

        builder.setContentText(formatDurationTime(settings)
                .plus(" ")
                .plus(context.getString(R.string.notification_steps_label))
                .plus(settings.workoutSteps - settings.totalSteps)
        )

        notificationManager.notify(TrackerServiceConstants.NOTIFICATION_ID, builder.build())
    }

    override fun createChannel() {
        notificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            TrackerServiceConstants.NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_MIN
        ).apply {
            description =  context.getString(R.string.channel_description)
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun checkNotInitialized() {
        if(!::builder.isInitialized) {
            throw UninitializedPropertyAccessException("NotificationCompat.Builder not initialized. Please ensure to call onStartCommand before starting a workout. ")
        }
    }

    private fun formatDurationTime(settings: TrackerNotificationSettings): String =
        settings.time.seconds.toComponents { hours, minutes, seconds, _ ->
            String.format(
                Locale.getDefault(),
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds,
            )
        }
}
