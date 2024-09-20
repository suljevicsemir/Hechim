package com.semirsuljevic.foundation.internal.sdk

import android.annotation.SuppressLint
import android.app.Service.START_NOT_STICKY
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.os.Handler
import android.os.Looper
import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerService
import com.semirsuljevic.foundation.api.sdk.TrackerServiceManager
import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class TrackerServiceManagerImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val notificationManager: TrackerNotificationManager
): TrackerServiceManager{
    override fun formatDurationTime(): String =
        _settings.value.time.seconds.toComponents { hours, minutes, seconds, _ ->
            String.format(
                Locale.getDefault(),
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds,
            )
        }


    @SuppressLint("MissingPermission")
    override fun startWorkout(service: TrackerService) {
        notificationManager.onStartCommand(service)
        mainHandler.post(object : Runnable {
            override fun run() {
                mainHandler.postDelayed(this, 1000)
                _settings.update { _settings.value.copy(time = _settings.value.time + 1) }
            }
        })
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        sensorManager.registerListener(
            service,
            stepSensor,
            SensorManager.SENSOR_DELAY_UI
        )
        mGpsLocationClient.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            3000,
            10.0f,
            locationListener
        )
    }

    override fun stopWorkout(service: TrackerService): Int{
        mainHandler.removeMessages(0)
        mGpsLocationClient.removeUpdates(locationListener)
        sensorManager.unregisterListener(service)
        service.stopSelf()
        notificationManager.stopWorkout()
        return START_NOT_STICKY
    }

    override fun resumeWorkout(): Int {
        _settings.update {
            _settings.value.copy(paused = false)
        }
        notificationManager.resumeWorkout()
        return START_NOT_STICKY
    }

    override fun pauseWorkout(): Int {
        _settings.update {
            _settings.value.copy(paused = true)
        }
        notificationManager.pauseWorkout()
        return START_NOT_STICKY
    }

    override val location: Flow<Location> get() = _location.asSharedFlow()
    override val settings: Flow<TrackerNotificationSettings> get() = _settings.asSharedFlow()


    private val _settings = MutableStateFlow(TrackerNotificationSettings())

    private val _location = MutableSharedFlow<Location>(replay = 1)

    private val mGpsLocationClient: LocationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
    private val sensorManager: SensorManager = applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager

    private val mainHandler = Handler(Looper.getMainLooper())

    private val locationListener = android.location.LocationListener { location ->
        if((_settings.value.initialPosition.provider ?: "").isEmpty()) {
            _settings.value.initialPosition = location
            _settings.value.initialPosition.provider = "updated"
            _settings.update {
                _settings.value.copy(initialPosition = location)
            }
            return@LocationListener
        }
        if(location.accuracy <= 11 && !_settings.value.paused) {
            val distance = _settings.value.initialPosition.distanceTo(location)
            _settings.update { _settings.value.copy(distance = _settings.value.distance + distance) }

        }
        _settings.update {
            _settings.value.copy(initialPosition = location)
        }
    }


}
