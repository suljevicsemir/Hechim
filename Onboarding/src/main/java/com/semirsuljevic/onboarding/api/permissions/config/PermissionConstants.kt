package com.semirsuljevic.onboarding.api.permissions.config

import android.Manifest
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.internal.permissions.PermissionType

object PermissionConstants {

    const val permissionRoute: String = "permission"
    const val android9LocationPermission = "android.Manifest.permission.Android9Permission"
    const val samsungManufacturer: String = "samsung"
    const val packageConstant: String = "package"
    /**
    Maximum number of requests on Android.
     */
    const val maxRequests: Int = 2

    val locationWhileInUse = PermissionConfig(
        titleKey = R.string.permission_while_using_location_title,
        descriptionKey = R.string.permission_while_using_location_description,
        questionButtonString = R.string.permission_while_using_location_sheet_button_title,
        nextButtonKey = R.string.permission_while_using_location_button_title,
        manifestPermission = Manifest.permission.ACCESS_FINE_LOCATION,
        permissionType = PermissionType.LocationWhileInUse,
        image = R.drawable.ic_location,
        sheetButtonKey = R.string.permission_location_sheet_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_location_sheet_description,
        sheetTitleKey = R.string.permission_location_sheet_title
    )

    val location9 = PermissionConfig(
        titleKey = R.string.permission_location9_title,
        descriptionKey = R.string.permission_location9_description,
        questionButtonString = R.string.permission_location9_sheet_button_title,
        nextButtonKey = R.string.permission_location9_button_title,
        manifestPermission = Manifest.permission.ACCESS_FINE_LOCATION,
        permissionType = PermissionType.BackgroundLocation,
        image = R.drawable.ic_location,
        sheetButtonKey = R.string.permission_location9_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_location9_sheet_description,
        sheetTitleKey = R.string.permission_location9_sheet_title
    )

    val locationAndroid10 = PermissionConfig(
        titleKey = R.string.permission_location10_title,
        descriptionKey = R.string.permission_location10_description,
        questionButtonString = R.string.permission_location10_sheet_button_title,
        nextButtonKey = R.string.permission_location10_button_title,
        manifestPermission = Manifest.permission.ACCESS_BACKGROUND_LOCATION,
        multiplePermissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION),
        permissionType = PermissionType.BackgroundLocation,
        image = R.drawable.ic_location,
        sheetButtonKey = R.string.permission_location10_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_location10_sheet_description,
        sheetTitleKey = R.string.permission_location10_sheet_title
    )

    val activityRecognition = PermissionConfig(
        titleKey = R.string.permission_activity_title,
        descriptionKey = R.string.permission_activity_description,
        questionButtonString = R.string.permission_activity_sheet_button_title,
        nextButtonKey = R.string.permission_activity_button_title,
        manifestPermission = Manifest.permission.ACTIVITY_RECOGNITION,
        permissionType = PermissionType.ActivityRecognition,
        image = R.drawable.ic_activity,
        sheetButtonKey = R.string.permission_activity_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_activity_sheet_description,
        sheetTitleKey = R.string.permission_activity_sheet_title
    )

    val bluetooth = PermissionConfig(
        titleKey = R.string.permission_bluetooth_title,
        descriptionKey = R.string.permission_bluetooth_description,
        questionButtonString = R.string.permission_bluetooth_sheet_button_title,
        nextButtonKey = R.string.permission_bluetooth_button_title,
        manifestPermission = Manifest.permission.BLUETOOTH_CONNECT,
        permissionType = PermissionType.Bluetooth,
        image = R.drawable.ic_bluetooth,
        sheetButtonKey = R.string.permission_bluetooth_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_bluetooth_sheet_description,
        sheetTitleKey = R.string.permission_bluetooth_sheet_title
    )

    val notification = PermissionConfig(
        titleKey = R.string.permission_notification_title,
        descriptionKey = R.string.permission_notification_description,
        questionButtonString = R.string.permission_notification_sheet_button_title,
        nextButtonKey = R.string.permission_notification_button_title,
        manifestPermission = Manifest.permission.POST_NOTIFICATIONS,
        permissionType = PermissionType.Notification,
        image = R.drawable.ic_notification,
        sheetButtonKey = R.string.permission_notification_sheet_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_notification_sheet_description,
        sheetTitleKey = R.string.permission_notification_sheet_title

    )



    val batteryOptimisation = PermissionConfig(
        titleKey = R.string.permission_battery_optimisation_title,
        descriptionKey = R.string.permission_battery_optimisation_description,
        questionButtonString = R.string.permission_battery_optimisation_sheet_button_title,
        nextButtonKey = R.string.permission_battery_optimisation_button_title,
        manifestPermission = Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
        permissionType = PermissionType.BatteryOptimization,
        image = R.drawable.ic_battery_optimisation,
        sheetButtonKey = R.string.permission_battery_optimisation_sheet_confirmation_button_title,
        sheetDescriptionKey = R.string.permission_battery_optimisation_sheet_description,
        sheetTitleKey = R.string.permission_battery_optimisation_sheet_title
    )

}
