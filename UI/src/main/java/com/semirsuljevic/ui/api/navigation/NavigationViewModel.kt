package com.semirsuljevic.ui.api.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){

    fun navigateTo(route: HechimRoute) = navigator.navigate(route)
    fun navigateTo(route: String) = navigator.navigate(route)
    fun pop() = navigator.pop()
}
