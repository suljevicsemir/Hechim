package com.semirsuljevic.onboarding.api.welcome.ui.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLanguageModel
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
internal fun LanguageSelectionItem(
    appLanguageModel: AppLanguageModel,
    onClick: () -> Unit,
    selected: Boolean,
    errorMessage: String = stringResource(id = R.string.language_selection_not_supported)
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(bottom = HechimTheme.sizes.medium)
            .clip(HechimTheme.shapes.xLargeRoundedCorner)
            .background(color = HechimTheme.colors.surfaceBackground)
            .clickable {
                onClick()
            }
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = appLanguageModel.image),
            contentDescription = null,
            modifier = Modifier
                .padding(start = HechimTheme.sizes.xLarge)
                .padding(vertical = HechimTheme.sizes.xLarge)
        )
        Spacer(modifier = Modifier.width(HechimTheme.sizes.large))
        Text(
            text = stringResource(id = appLanguageModel.text),
            color = HechimTheme.colors.textDefault,
            style = HechimTheme.fonts.bodyEmphasized
        )
        Spacer(modifier = Modifier.weight(1f))
        if(selected) {
            Image(painter = painterResource(id = R.drawable.ic_radio_on), contentDescription = null)
        }
        else {
            Image(painter = painterResource(id = R.drawable.ic_radio_off), contentDescription = null)
        }
        Spacer(modifier = Modifier.width(HechimTheme.sizes.xLarge))

    }
}
@Preview
@Composable
fun LangaugeSelectionItemPreview() {
    LanguageSelectionItem(
        appLanguageModel = AppLanguageModel(
            image = R.drawable.ic_language_en,
            text = R.string.app_language_en,
            locale = AppLocale.English,
        ),
        selected = true,
        onClick = {

        }
    )
}
