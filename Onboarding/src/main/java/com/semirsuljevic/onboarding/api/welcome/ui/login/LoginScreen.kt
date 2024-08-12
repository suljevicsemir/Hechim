package com.semirsuljevic.onboarding.api.welcome.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.buttons.HechimIconButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.paging.PageIndexIndicator
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme
import kotlinx.coroutines.launch

class RouteLogin: HechimRoute("login")

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    permissionsViewModel: PermissionViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    HechimScreen (
        resource = loginViewModel.resource,
        config = HechimScreenConfig(
            title = stringResource(id = R.string.login_password_input_title),
            canNavigateBack = false,
            errorReset = {
                loginViewModel.resetState()
            }
        ),
        actions = {
            HechimIconButton(
                icon = R.drawable.ic_help,
                onClick = {
                    loginViewModel.navigate()
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
                stringResource(id = R.string.login_password_input_headline),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyLargeNoSpacing
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            Text(
                stringResource(id = R.string.login_password_input_desc_1).format(loginViewModel.email),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular
            )
            Spacer(modifier = Modifier.weight(0.1f))
            HechimTextField(
                value = loginViewModel.password,
                onValueChange = { password ->
                    loginViewModel.onPasswordChange(password)
                },
                hint = stringResource(id = R.string.login_password_input_hint),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = {
                    coroutineScope.launch {
                        loginViewModel.login {
                            permissionsViewModel.startPermissions()
                        }
                    }
                },
                text = stringResource(id = R.string.login_password_input_login_button)
            )
        }
    }
}



@Preview
@Composable
private fun LoginRoutePreview() {
    LoginScreen(hiltViewModel(), hiltViewModel())
}
