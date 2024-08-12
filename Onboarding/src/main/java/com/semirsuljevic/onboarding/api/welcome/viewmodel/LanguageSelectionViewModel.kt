package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLanguageModel
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.onboarding.api.welcome.config.welcome.toAppLocale
import com.semirsuljevic.foundation.api.secure.SecureStorage
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val secureStorage: SecureStorage,
    private val navigator: Navigator
): ViewModel() {

    private val _dialogVisible = mutableStateOf(false)
    val dialogVisible: Boolean get() = _dialogVisible.value

    /**
        Represents currently selected locale - not the saved one.
        Necessary to have this because of confirmation dialog, gives info
        to dialog which language to display.
        "Are you sure you want to change your language to selectedLocale.value".
        Basically when language is selected, not confirmed, value to this is assigned.
     */

    private val _selectedLocale = mutableStateOf<AppLanguageModel?>(null)
    val selectedLocale: AppLanguageModel? get() = _selectedLocale.value

    private val _savedLocale = mutableStateOf(AppLocale.English)
    val savedLocale: AppLocale get() = _savedLocale.value


    init {
        _savedLocale.value = secureStorage.getStringValue(SecureStorage.locale).toAppLocale()
    }

    /** Invoked when user selects any language, not confirms.
        If that language is already saved to be used, nothing happens.
        If not, sets value of the _selectedLocale and prompts confirmation dialog.
     */
    fun selectLocale(appLanguageModel: AppLanguageModel) {
        if(_savedLocale.value == appLanguageModel.locale) {
            return
        }
        _selectedLocale.value = appLanguageModel
        toggleDialog()
    }

    /**
        Toggle dialog visibility, based way to control if the dialog is visible or not.
        This way caller has not direct access to boolean flag and logic is hidden.
     */
    fun toggleDialog() {
        _dialogVisible.value = !_dialogVisible.value
    }

    /**
        Called from confirmation dialog, we guard are selves with null check, even tho
        this case should never happen.
        Saves locale to storage and dismissed dialog.
     */
    fun confirmLocaleChange() {
        if(_selectedLocale.value == null) {
            return
        }
        _savedLocale.value = _selectedLocale.value!!.locale
        secureStorage.storeStringValue(SecureStorage.locale, _savedLocale.value.locale)
        toggleDialog()
    }

    /**
        Lists of languages to be displayed on the UI.
     */
    val languages = listOf(
        AppLanguageModel(
            image = R.drawable.ic_language_fr,
            text = R.string.app_language_fr,
            locale = AppLocale.French,
        ),
        AppLanguageModel(
            image = R.drawable.ic_language_en,
            text = R.string.app_language_en,
            locale = AppLocale.English
        ),
    )

    fun pop() = navigator.pop()

    fun navigateToEmail() {
        navigator.navigate(RouteEmail())
    }

    val dialogLanguage: UiText get() = if(_selectedLocale.value == null)
        UiText.StringValue("")
    else UiText.StringResource(_selectedLocale.value!!.text)
}
