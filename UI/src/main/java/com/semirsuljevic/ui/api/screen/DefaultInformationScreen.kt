package com.semirsuljevic.ui.api.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.theme.HechimTheme

data class DefaultInformationScreenConfig(
    val backgroundColor: Color = HechimTheme.colors.backgroundSecondary,
    val image: Int,
    val title: UiText,
    val description: UiText,
    val button: UiText,
    val onButtonClick: () -> Unit
)

@Composable
fun DefaultInformationScreen(
    config: DefaultInformationScreenConfig
) {
    Scaffold (
        containerColor = config.backgroundColor
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = config.backgroundColor)
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.3f))
            Image(
                painter = painterResource(id = config.image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxxLarge))
            Text(
                config.title.asString(),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyLarge
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            Text(
                config.description.asString(),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = config.onButtonClick,
                text = config.button.asString()
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultInformationScreen(
        config = DefaultInformationScreenConfig(
            image = R.drawable.img_nothing_found,
            title = UiText.StringValue("Password changed"),
            description = UiText.StringValue("Your password for accessing Eugen app was changed successfully."),
            button = UiText.StringValue("OK"),
            onButtonClick = {

            }
        )
    )
}
