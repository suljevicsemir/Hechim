package com.semirsuljevic.ui.api.theme

import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object HechimFonts {
    val bodyLarge = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.W600, letterSpacing = 0.2.sp)

    val bodyLargeNoSpacing = TextStyle(fontSize = 40.sp, letterSpacing = (-1).sp)
    val bodyRegular = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400)


    val bodyEmphasized = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W500)


    val pageTitle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W500, letterSpacing = (-0.27).sp)
    val pageTitleAlt = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W500)
    val regularTitle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.W500)

    val buttonText = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W500)

    val hint = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400)

    val labelXSmall = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    val labelXXSmall = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)


}
