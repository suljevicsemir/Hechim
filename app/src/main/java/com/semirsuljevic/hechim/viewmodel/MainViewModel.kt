package com.semirsuljevic.hechim.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.semirsuljevic.dashboard.api.ui.RouteDashboard
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.storage.preferences.AppPreferences
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.onboarding.api.permissions.PermissionsApi
import com.semirsuljevic.onboarding.api.welcome.config.welcome.OnBoardingConstants
import com.semirsuljevic.onboarding.api.welcome.ui.login.RouteLogin
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.RouteName
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val permissionsApi: PermissionsApi
): ViewModel(){
    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination: String? get() = _startDestination.value

    fun setupNavigation(navController: NavHostController) {
        navigator.setNavController(navController)
        navigator.setHome(RouteDashboard().path)
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
