package com.semirsuljevic.onboarding.api.welcome.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.viewmodel.RegisterViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.buttons.HechimIconButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteRegister:HechimRoute("register")

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    HechimScreen (
        resource = viewModel.resource,
        config = HechimScreenConfig(
            canNavigateBack = false,
            title = stringResource(id = R.string.register_title),
            errorReset = viewModel::resetState
        ),
        actions = { HechimIconButton(icon = R.drawable.ic_help, onClick = viewModel::navigate) }
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
                .padding(bottom = HechimTheme.sizes.xxLarge)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            Text(
                stringResource(id = R.string.register_headline),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyLargeNoSpacing
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            Text(
                stringResource(id = R.string.register_desc),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular
            )
            Spacer(modifier = Modifier.weight(0.1f))
            HechimTextField(
                onValueChange = viewModel::setEmail,
                config = viewModel.emailConfig
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.small))
            HechimTextField(
                onValueChange = viewModel::setPassword,
                trailingIcon = viewModel.passwordIcon,
                onTrailingClick = viewModel::invertPasswordVisibility,
                config = viewModel.passwordConfig
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.small))
            HechimTextField(
                onValueChange = viewModel::setConfirmPassword,
                trailingIcon = viewModel.confirmPasswordIcon,
                onTrailingClick = viewModel::invertConfirmPasswordVisibility,
                config = viewModel.confirmPasswordConfig
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = viewModel::register,
                text = stringResource(id = R.string.register_button)
            )
        }
    }
}
