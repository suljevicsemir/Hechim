package com.semirsuljevic.dashboard.api.settings.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsList: SettingsList
): ViewModel(){

    private val _list = mutableStateOf<List<HechimListItemConfig>>(emptyList())
    val list get() = _list.value

    init {
        collectList()
    }

    private fun collectList() {
        viewModelScope.launch {
            settingsList.settingsList.collectLatest {
                _list.value = it
            }
        }
    }
}
