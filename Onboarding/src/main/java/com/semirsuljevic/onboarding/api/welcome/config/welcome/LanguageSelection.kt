package com.semirsuljevic.onboarding.api.welcome.config.welcome

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val locale: AppLocale? = null,
    val isFirstRun: Boolean = true
)

data class AppLanguageModel(
    val image: Int,
    @StringRes val text: Int,
    val locale: AppLocale
)


enum class AppLocale(val locale: String) {
    English("en"),
    French("fr"),
    Unsupported("")
}

fun String?.toAppLocale() : AppLocale{
    return when(this) {
        "fr" -> AppLocale.French
        else -> AppLocale.English
    }
}
