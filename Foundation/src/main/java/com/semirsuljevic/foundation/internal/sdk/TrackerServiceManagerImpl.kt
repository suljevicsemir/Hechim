package com.semirsuljevic.foundation.internal.sdk

import android.annotation.SuppressLint
import android.app.Service.START_NOT_STICKY
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.os.Handler
import android.os.Looper
import com.semirsuljevic.foundation.api.common.Dispatchers
import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerService
import com.semirsuljevic.foundation.api.sdk.TrackerServiceManager
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants
import com.semirsuljevic.foundation.api.sdk.model.TrackerNotificationSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class TrackerServiceManagerImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val notificationManager: TrackerNotificationManager,
    private val dispatchers: Dispatchers
): TrackerServiceManager{
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
    @SuppressLint("MissingPermission")
    override fun startWorkout(service: TrackerService) {
        notificationManager.onStartCommand(service)
        observeNotification()

        CoroutineScope(dispatchers.io).launch {
            delay(TrackerServiceConstants.NOTIFICATION_DELAY)
            mainHandler.post(object : Runnable {
                override fun run() {
                    mainHandler.postDelayed(this, TrackerServiceConstants.NOTIFICATION_PERIOD)
                    if(!_settings.value.paused) {
                        _settings.update { _settings.value.copy(time = _settings.value.time + 1) }
                    }
                }
            })
        }

        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor != null) {
            sensorManager.registerListener(
                service,
                stepSensor,
                SensorManager.SENSOR_DELAY_UI
            )
        }

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
        _settings.update { _settings.value.copy(paused = false) }
        notificationManager.resumeWorkout()
        return START_NOT_STICKY
    }

    override fun pauseWorkout(): Int {
        _settings.update { _settings.value.copy(paused = true) }
        notificationManager.pauseWorkout()
        return START_NOT_STICKY
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event == null) {
            return
        }
        if(_settings.value.totalSteps == -1) {
            _settings.update {
                _settings.value.copy(totalSteps = event.values[0].toInt())
            }
        }
        _settings.update {
            _settings.value.copy(workoutSteps = event.values[0].toInt())
        }
    }

    override fun startService() {
        val serviceIntent = Intent(applicationContext, TrackerService::class.java)
        serviceIntent.action = TrackerServiceConstants.START_WORKOUT
        applicationContext.startService(serviceIntent)
    }

    private fun observeNotification() {
        CoroutineScope(dispatchers.main).launch {
            settings.collectLatest {
                notificationManager.updateNotification(it)
            }
        }
    }


    override val settings: Flow<TrackerNotificationSettings> get() = _settings.asSharedFlow()

    private val _settings = MutableStateFlow(TrackerNotificationSettings())
    private val mGpsLocationClient: LocationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
    private val sensorManager: SensorManager = applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager
    private val mainHandler = Handler(Looper.getMainLooper())




}
