package com.semirsuljevic.dashboard.api.applicationSettings.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationSettingsViewModel @Inject constructor(
    private val applicationSettingsList: ApplicationSettingsList
):ViewModel(){

    private val _list = mutableStateOf<List<HechimListItemConfig>>(emptyList())
    val list get() = _list.value

    init {
        collectList()
    }

    private fun collectList() {
        viewModelScope.launch {
            applicationSettingsList.list.collectLatest {
                _list.value = it
            }
        }
    }

}
