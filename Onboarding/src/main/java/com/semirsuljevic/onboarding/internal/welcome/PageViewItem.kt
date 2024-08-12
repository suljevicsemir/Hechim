package com.semirsuljevic.onboarding.internal.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.config.welcome.OnBoardingItem
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
internal fun PageViewItem(
    item: OnBoardingItem
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = HechimTheme.sizes.xLarge)
        )
        Column (
            modifier = Modifier
                .padding(
                    horizontal = HechimTheme.sizes.xxLarge,
                    vertical = HechimTheme.sizes.medium
                )
        ){
            Text(
                text = stringResource(id = item.headlineKey),
                modifier = Modifier.padding(bottom = HechimTheme.sizes.large),
                style = HechimTheme.fonts.bodyLarge,
                color = HechimTheme.colors.textDefault,
            )
            Column {
                Text(
                    text = stringResource(id = item.descriptionKey),
                    style = HechimTheme.fonts.bodyRegular,
                    color = HechimTheme.colors.textDefault,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PageViewItemPreview() {
    PageViewItem(
        OnBoardingItem(
            headlineKey = R.string.onboarding_1_headline,
            descriptionKey = R.string.onboarding_1_desc,
            image = R.drawable.img_onboarding_1
        )
    )
}
