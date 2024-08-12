package com.semirsuljevic.onboarding.internal.permissions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.common.HechimModalBottomSheet
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PermissionSheet(
    title: UiText,
    description: UiText,
    button: UiText,
    viewModel: PermissionViewModel
) {
    val visible by viewModel.sheetVisible.collectAsStateWithLifecycle()


    HechimModalBottomSheet(
        onDismissRequest = {
            viewModel.setSheetVisibility()
        },
        visible = visible,
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                title.asString(),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.regularTitle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.small))
            HechimHtmlText(
                description.asString(),
                style = TextStyle(
                    color = HechimTheme.colors.textDefault,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                ),
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
            HechimButton(
                onClick = {
                    viewModel.setSheetVisibility()
                },
                enabled = true,
                text = button.asString(),
            )
        }
    }
}

@Preview
@Composable
fun PermissionSheetPreview() {
    PermissionSheet(
        title = UiText.StringValue("title"),
        description = UiText.StringValue("description"),
        viewModel = hiltViewModel(),
        button = UiText.StringValue("description")
    )
}
