package com.semirsuljevic.ui.api.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.theme.HechimTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimDialog(
    config: HechimDialogConfig,
) {
    if(!config.visible) {
        return
    }
    AlertDialog(
        onDismissRequest = { config.onDismiss() },
        content = {
            Column (
                modifier = Modifier
                    .clip(HechimTheme.shapes.largeRoundedCorner)
                    .background(HechimTheme.colors.surfaceBackground)
                    .padding(HechimTheme.sizes.large)
            ){
                Text(
                    config.title.asString(),
                    style = HechimTheme.fonts.regularTitle.copy(fontSize = 24.sp),
                    color = HechimTheme.colors.textDefault
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
                Text(
                    config.description.asString(),
                    color = HechimTheme.colors.textDefault,
                    style = HechimTheme.fonts.bodyRegular
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    TextButton(
                        onClick = { config.onDismiss() },
                        content = {
                            Text(
                                config.dismissButton.asString(),
                                color = HechimTheme.colors.textDefault
                            )
                        }
                    )
                    TextButton(
                        onClick = { config.onConfirm() },
                        content = {
                            Text(
                                config.confirmButton.asString(),
                                color = HechimTheme.colors.textDefault
                            )
                        }
                    )
                }
            }
        },
    )
}

@Preview
@Composable
fun HechimDialogPreview() {
    HechimDialog(
        config = HechimDialogConfig(
            visible = true,
            confirmButton = UiText.StringValue("Yes"),
            dismissButton = UiText.StringValue("No"),
            onDismiss = {},
            onConfirm = {},
            title = UiText.StringValue("Dialog title"),
            description = UiText.StringValue("Dialog title")
        )
    )
}
