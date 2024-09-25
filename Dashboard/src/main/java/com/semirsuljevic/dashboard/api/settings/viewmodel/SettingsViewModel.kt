package com.semirsuljevic.dashboard.api.settings.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.sdk.TrackerService
import com.semirsuljevic.foundation.api.sdk.TrackerServiceManager
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsList: SettingsList,
    private val trackerService: TrackerServiceManager
): ViewModel(){

    private val _list = mutableStateOf<List<HechimListItemConfig>>(emptyList())
    val list get() = _list.value

    init {
        collectList()
    }

    fun start() {
        trackerService.startService()
    }

    private fun collectList() {
        viewModelScope.launch {
            settingsList.settingsList.collectLatest {
                _list.value = it
            }
        }
    }
}
