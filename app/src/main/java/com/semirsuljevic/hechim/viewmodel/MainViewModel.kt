package com.semirsuljevic.hechim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.onboarding.api.welcome.ui.login.RouteLogin
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.RouteName
import com.semirsuljevic.onboarding.api.welcome.ui.register.RouteRegister
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication,
    private val profileProvider: ProfileProvider,
    private val profile: Profile
): ViewModel(){

    fun setupNavigation(navController: NavHostController) {
        navigator.setNavController(navController)
        //navigator.setHome(RouteDashboard().path)
    }

    fun checkProfileTrapdoor() {
        if(!hechimAuthentication.isAuthenticated()) {
            return
        }
        viewModelScope.launch {
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

    val startDestination : HechimRoute
        get() {
//        if(hechimAuthentication.isAuthenticated()) {
//            return RouteDashboard()
//        }
        return RouteLogin()
    }
}
