package com.semirsuljevic.dashboard.internal.legal

import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.legal.ui.RoutePrivacyPolicy
import com.semirsuljevic.dashboard.api.legal.ui.RouteTerms
import com.semirsuljevic.dashboard.api.legal.viewmodel.LegalList
import com.semirsuljevic.dashboard.api.ui.RouteDashboard
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

internal class LegalListImpl @Inject constructor(
    private val navigator: Navigator
): LegalList{
    override val legalItems: Flow<List<HechimListItemConfig>> get() = _legalItems.asSharedFlow()

    private val _legalItems = MutableStateFlow(
        value = listOf(
            HechimListItemConfig(
                title = UiText.StringResource(R.string.legal_page_terms_title),
                description = UiText.StringResource(R.string.legal_page_terms_desc),
                onClick = { navigator.navigate(RouteTerms()) },
                icon = R.drawable.ic_legal_info
            ),
            HechimListItemConfig(
                title = UiText.StringResource(R.string.legal_page_privacy_title),
                description = UiText.StringResource(R.string.legal_page_privacy_desc),
                onClick = { navigator.navigate(RoutePrivacyPolicy()) },
                icon = R.drawable.ic_legal_info)

        )
    )
}
