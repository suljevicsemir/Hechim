package com.semirsuljevic.ui.api.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.img_info_flag,
    requirements: UiText
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
        Text(
            requirements.asString(),
            style = HechimTheme.fonts.bodyRegular,
            color = HechimTheme.colors.textFieldInactive,
            textAlign = TextAlign.Center
        )
    }
}
