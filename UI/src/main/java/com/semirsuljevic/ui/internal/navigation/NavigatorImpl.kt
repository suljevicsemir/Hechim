package com.semirsuljevic.ui.internal.navigation

import android.content.res.Resources.NotFoundException
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor(): Navigator {
    private lateinit var navigator: NavHostController
    private var homePath: String = ""
    override fun setNavController(navController: NavHostController) {
        navigator = navController
    }

    override fun navigate(route: HechimRoute) {
        navigator.navigate(route.path)
    }

    override fun navigate(path: String) {
        navigator.navigate(path)
    }

    override fun pop() {
        navigator.navigateUp()
    }

    override fun setHome(path: String) {
        homePath = path
    }

    override fun navigateHome() {
        if(homePath.isEmpty()) {
            throw NotFoundException("Home path not set, call setHome() before using navigateHome()")
        }
        navigator.navigate(homePath) {
            popUpTo(navigator.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }

    override fun navigateAndRemove(route: HechimRoute) {
        navigator.navigate(route.path) {
            popUpTo(navigator.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
}
