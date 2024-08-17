package com.semirsuljevic.onboarding.api.welcome.ui.register

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.buttons.HechimTextButton
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
internal fun SignInButton(
    onClick: () -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            UiText.StringResource(R.string.register_have_account).asString(),
            style = HechimTheme.fonts.bodyEmphasized,
            color = HechimTheme.colors.textFieldInactive
        )
        HechimTextButton(
            onClick = onClick,
            text = UiText.StringResource(R.string.register_sign_in).asString(),
            textColor = HechimTheme.colors.primary
        )
    }
}
