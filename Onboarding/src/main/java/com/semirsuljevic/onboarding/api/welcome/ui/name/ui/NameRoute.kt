package com.semirsuljevic.onboarding.api.welcome.ui.name.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.name.viewmodel.NameViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme


class RouteName: HechimRoute("name")

@Composable
fun NameRoute(
    viewModel: NameViewModel = hiltViewModel()
) {
    HechimScreen (
        config = HechimScreenConfig(
            canNavigateBack = false
        ),
        resource = viewModel.state
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            Text(
                UiText.StringResource(R.string.name_headline).asString(),
                style = HechimTheme.fonts.regularTitle,
                color = HechimTheme.colors.textDefault
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            HechimTextField(
                onValueChange = viewModel::onFirstNameChange,
                config = viewModel.firstNameConfig
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
            HechimTextField(
                onValueChange = viewModel::onLastNameChange,
                config = viewModel.lastNameConfig,
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = { viewModel.setInfo() },
                text = "Continue",
                enabled = viewModel.buttonEnabled,

            )
        }
    }
}
