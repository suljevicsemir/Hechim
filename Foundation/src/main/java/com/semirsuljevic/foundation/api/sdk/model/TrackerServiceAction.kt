package com.semirsuljevic.foundation.api.sdk.model

import android.content.Intent
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants

enum class TrackerServiceAction {
    START,
    STOP,
    RESUME,
    PAUSE,
    UNKNOWN;

    companion object {
        fun fromIntentAction(intent: Intent?): TrackerServiceAction = when(intent?.action) {
            null -> UNKNOWN
            TrackerServiceConstants.START_WORKOUT -> START
            TrackerServiceConstants.STOP_WORKOUT -> STOP
            TrackerServiceConstants.PAUSE_WORKOUT -> PAUSE
            TrackerServiceConstants.RESUME_WORKOUT -> RESUME
            else -> UNKNOWN
        }
    }
}
