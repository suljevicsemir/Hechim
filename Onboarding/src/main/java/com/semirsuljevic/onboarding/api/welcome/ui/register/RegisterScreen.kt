package com.semirsuljevic.onboarding.api.welcome.ui.register

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.viewmodel.RegisterViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.buttons.HechimIconButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.paging.PageIndexIndicator
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteRegister:HechimRoute("register")

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel
) {
    HechimScreen (
        resource = registerViewModel.resource,
        config = HechimScreenConfig(
            canNavigateBack = false,
            title = stringResource(id = R.string.register_title),
            errorReset = {
                registerViewModel.resetState()
            }
        ),
        actions = {
            HechimIconButton(
                icon = R.drawable.ic_help,
                onClick = {
                    registerViewModel.navigate()
                }
            )
        }
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
                .padding(bottom = HechimTheme.sizes.xxLarge)
                .fillMaxSize()
        ){
            Spacer(modifier = Modifier.weight(0.2f))
            Row {
                PageIndexIndicator(selected = false)
                PageIndexIndicator(selected = true)
            }
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
                value = registerViewModel.password,
                onValueChange = { password ->
                    registerViewModel.setPassword(password)
                },
                hint = stringResource(id = R.string.register_password_hint)
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            HechimTextField(
                value = registerViewModel.confirmPassword,
                onValueChange = { confirmPassword ->
                    registerViewModel.setConfirmPassword(confirmPassword)
                },
                hint = stringResource(id = R.string.register_confirm_password_hint),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = {
                    registerViewModel.register()
                },
                text = stringResource(id = R.string.register_button)
            )
        }
    }
}
