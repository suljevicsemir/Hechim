package com.semirsuljevic.dashboard.api.legal.viewmodel

import com.semirsuljevic.ui.api.common.HechimListItemConfig
import kotlinx.coroutines.flow.Flow

interface LegalList {
    val legalItems: Flow<List<HechimListItemConfig>>
}
