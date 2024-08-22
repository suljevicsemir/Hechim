package com.semirsuljevic.dashboard.api.applicationSettings.viewmodel

import com.semirsuljevic.ui.api.common.HechimListItemConfig
import kotlinx.coroutines.flow.Flow

interface ApplicationSettingsList {

    val list: Flow<List<HechimListItemConfig>>
}
