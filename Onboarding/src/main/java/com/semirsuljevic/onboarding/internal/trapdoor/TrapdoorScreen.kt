package com.semirsuljevic.onboarding.internal.trapdoor

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.semirsuljevic.onboarding.api.trapdoor.config.TrapdoorConfig
import com.semirsuljevic.onboarding.api.trapdoor.viewmodel.TrapdoorViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun TrapdoorScreen(
    config: TrapdoorConfig?,
    trapdoorViewModel: TrapdoorViewModel
) {

    if(config == null) {
        return
    }
    val launcher: ManagedActivityResultLauncher<Intent, ActivityResult> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {

        }
    )

    Scaffold (
        containerColor = HechimTheme.colors.backgroundDefault
    ){
        Column (
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(HechimTheme.sizes.scaffoldHorizontal),
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = config.icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xSmall))
                Text(
                    config.title.asString(),
                    style = HechimTheme.fonts.regularTitle,
                    color = HechimTheme.colors.textDefault
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
                Text(
                    config.description.asString(),
                    style = HechimTheme.fonts.bodyEmphasized,
                    color = HechimTheme.colors.textDefault
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
                HechimHtmlText(
                    html = config.steps.asString(),
                    style = TextStyle(
                        color = HechimTheme.colors.textDefault,
                        fontSize = HechimTheme.fonts.bodyRegular.fontSize
                    )
                )
            }
            HechimButton(
                onClick = {
                    trapdoorViewModel.fixPermission(launcher)
                },
                enabled = true,
                text = config.button.asString(),
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
        }

    }

}
