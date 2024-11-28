package com.semirsuljevic.hechim.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.hechimdemo.onboarding.R
import com.semirsuljevic.dashboard.api.ui.RouteDashboard
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.storage.preferences.AppPreferences
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.hechim.MainActivity
import com.semirsuljevic.onboarding.api.permissions.PermissionsApi
import com.semirsuljevic.onboarding.api.welcome.config.welcome.OnBoardingConstants
import com.semirsuljevic.onboarding.api.welcome.ui.login.RouteLogin
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.RouteName
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication,
    private val profileProvider: ProfileProvider,
    private val profile: Profile,
    private val appPreferences: AppPreferences,
    private val permissionsApi: PermissionsApi,
    @ApplicationContext private val context: Context
): ViewModel(){
    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination: String? get() = _startDestination.value

    fun setupNavigation(navController: NavHostController) {
        navigator.setNavController(navController)
        navigator.setHome(RouteDashboard().path)
    }

    fun createPinnedShortcut() {
        // Check if shortcut pinning is supported
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        if (shortcutManager?.isRequestPinShortcutSupported == true) {
            // Create an intent for the shortcut's target activity
            val shortcutIntent = Intent(context, MainActivity::class.java).apply {
                action = "START"
            }

            // Define the shortcut
            val shortcut = ShortcutInfo.Builder(context, "shortcut_id")
                .setShortLabel("Shortcut Name")
                .setLongLabel("Longer Shortcut Description")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_show))
                .setIntent(shortcutIntent)
                .build()

            // Request the shortcut be pinned to the home screen
            shortcutManager.requestPinShortcut(shortcut, null)
        }
    }


    fun checkProfileTrapdoor() {
        viewModelScope.launch {
            if(!hechimAuthentication.isAuthenticated() || permissionsApi.checkPermissions().isNotEmpty()) {
                return@launch
            }
            delay(1000)
            profile.getUser()
            launch {
                profileProvider.profileFlow.collectLatest {
                    if(!it.completedName) {
                        navigator.navigate(RouteName())
                    }
                }
            }
        }
    }

    init {
        collectStartDestination()
    }

    private fun collectStartDestination() {
        viewModelScope.launch {
            if(hechimAuthentication.isAuthenticated()) {
                _startDestination.value = RouteDashboard().path
                return@launch
            }
            val finishedOnboarding = appPreferences.getBoolean(OnBoardingConstants.FINISHED_ONBOARDING).first() ?: false
            if(finishedOnboarding) {
                _startDestination.value = RouteLogin().path
                return@launch
            }
            _startDestination.value = RouteOnBoarding().path
        }
    }

}
