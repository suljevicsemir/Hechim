package com.semirsuljevic.onboarding.api.trapdoor.config
import android.Manifest
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.theme.HechimTheme

object TrapdoorConstants {

    const val packageConstant: String = "package"
    const val trapdoorDelay: Long = 100L
    const val appNameString: String = "app-name"

    //Android 10 and above
    val locationAlways = TrapdoorConfig(
        icon = R.drawable.ic_trapdoor_warning,
        title = UiText.StringResource(R.string.trapdoor_location_always_title),
        description = UiText.StringResource(
            R.string.trapdoor_location_always_description,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName
        ),
        steps = UiText.StringResource(R.string.trapdoor_location_always_steps),
        button = UiText.StringResource(R.string.trapdoor_location_always_button),
        manifestPermission = Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

    //Android 9 and below
    val location = TrapdoorConfig(
        icon = R.drawable.ic_trapdoor_warning,
        title = UiText.StringResource(R.string.trapdoor_location_title),
        description = UiText.StringResource(R.string.trapdoor_location_description,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName
        ),
        steps = UiText.StringResource(R.string.trapdoor_location_steps,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName
        ),
        button = UiText.StringResource(R.string.trapdoor_location_button),
    )

    val batteryOptimization = TrapdoorConfig(
        icon = R.drawable.ic_trapdoor_warning,
        title = UiText.StringResource(R.string.trapdoor_battery_optimisation_title),
        description = UiText.StringResource(R.string.trapdoor_battery_optimisation_description,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName
        ),
        steps = UiText.StringResource(R.string.trapdoor_battery_optimisation_steps,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName),
        button = UiText.StringResource(R.string.trapdoor_battery_optimisation_button),
        manifestPermission = Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    )

    val activityRecognition = TrapdoorConfig(
        icon = R.drawable.ic_trapdoor_warning,
        title = UiText.StringResource(R.string.trapdoor_activity_recognition_title),
        description = UiText.StringResource(R.string.trapdoor_activity_recognition_description,
            HechimTheme.brand.appName,
            HechimTheme.brand.appName),
        steps = UiText.StringResource(R.string.trapdoor_activity_recognition_steps),
        button = UiText.StringResource(R.string.trapdoor_activity_recognition_button),
        manifestPermission = Manifest.permission.ACTIVITY_RECOGNITION
    )
}
