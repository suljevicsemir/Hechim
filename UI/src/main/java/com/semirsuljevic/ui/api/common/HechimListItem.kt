package com.semirsuljevic.ui.api.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.theme.HechimTheme

data class HechimListItemConfig(
    val title: UiText,
    val description: UiText,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun HechimListItem(
    modifier: Modifier = Modifier,
    config: HechimListItemConfig
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(vertical = HechimTheme.sizes.xSmall)
            .clip(HechimTheme.shapes.xLargeRoundedCorner)
            .background(color = HechimTheme.colors.surfaceBackground)
            .clickable {
                config.onClick.invoke()
            }
            .padding(
                horizontal = HechimTheme.sizes.xLarge,
                vertical = HechimTheme.sizes.large
            )
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = config.icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(width = HechimTheme.sizes.large, height = 0.dp))
        Column (modifier = Modifier.weight(1f)){
            Text(
                config.title.asString(),
                style = HechimTheme.fonts.bodyEmphasized,
                color = HechimTheme.colors.textDefault
            )
            Text(
                config.description.asString(),
                style = HechimTheme.fonts.labelXXSmall,
                color = HechimTheme.colors.textFieldInactive
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun HechimListItemPreview() {
    HechimListItem(
        config = HechimListItemConfig(
            title = UiText.StringValue("User profile"),
            description = UiText.StringValue("View your profile data"),
            icon = R.drawable.ic_arrow_right,
            onClick = {

            }
        )
    )
}
