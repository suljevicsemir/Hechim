package com.semirsuljevic.dashboard.api.settings.viewmodel

import com.semirsuljevic.ui.api.common.HechimListItemConfig
import kotlinx.coroutines.flow.Flow

interface SettingsList {

    val settingsList: Flow<List<HechimListItemConfig>>
}
