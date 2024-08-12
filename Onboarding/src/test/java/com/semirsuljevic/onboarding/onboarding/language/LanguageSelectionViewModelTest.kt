package com.semirsuljevic.onboarding.onboarding.language

import com.google.common.truth.Truth.assertThat
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.foundation.api.secure.SecureStorage
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LanguageSelectionViewModel
import com.semirsuljevic.onboarding.onboarding.util.BaseMockkTest
import com.semirsuljevic.ui.api.navigation.Navigator
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LanguageSelectionViewModelTest: BaseMockkTest<LanguageSelectionViewModel>(){

    @MockK
    private lateinit var secureStorage: SecureStorage
    @MockK
    private lateinit var navigator: Navigator

    override fun createSut(): LanguageSelectionViewModel = LanguageSelectionViewModel(
        secureStorage, navigator
    )

    @BeforeEach
    override fun setUp() {
        every { secureStorage.getStringValue(SecureStorage.locale) } returns "en"
    }

    @Test
    fun `test constructor french`() = runTest(UnconfinedTestDispatcher()){
        every { secureStorage.getStringValue(SecureStorage.locale) }returns AppLocale.French.locale
        val stub = createSut()

        assertThat(stub.savedLocale.locale).isEqualTo(AppLocale.French.locale)
    }

    @Test
    fun `test constructor english`() = runTest(UnconfinedTestDispatcher()){
        every { secureStorage.getStringValue(SecureStorage.locale) } returns AppLocale.English.locale
        val stub = createSut()

        assertThat(stub.savedLocale.locale).isEqualTo(AppLocale.English.locale)
    }

    @Test
    fun `test selected locale - dialog value`() = runTest(UnconfinedTestDispatcher()){
        every { secureStorage.getStringValue(SecureStorage.locale) } returns AppLocale.English.locale
        val stub = createSut()

        //initially should be null
        assertThat(stub.selectedLocale).isEqualTo(null)
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.English })
        //still should be null as the same language was selected
        assertThat(stub.selectedLocale).isEqualTo(null)
        //select new language
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.French })
        assertThat(stub.selectedLocale?.locale).isEqualTo(AppLocale.French)
    }



    @Test
    fun `test confirmation`() = runTest(UnconfinedTestDispatcher()){
        //ensure saved language is english
        every { secureStorage.getStringValue(SecureStorage.locale) } returns AppLocale.English.locale
        val stub = createSut()
        //initial value - saved locale is english
        assertThat(stub.savedLocale).isEqualTo(AppLocale.English)
        //initial value - selected locale is null
        assertThat(stub.selectedLocale).isEqualTo(null)
        //try to select the same language that's currently saved
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.English })
        //confirmation dialog shouldn't be visible
        assertThat(stub.dialogVisible).isFalse()
        //select French - language that is not currently selected
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.French })
        //dialog should be visible
        assertThat(stub.dialogVisible).isTrue()
        //dismiss dialog - aborted confirmation
        stub.toggleDialog()
        assertThat(stub.dialogVisible).isFalse()
        //select french again
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.French })
        //confirm change
        stub.confirmLocaleChange()
        //dialog should be closed and saved locale should be changed
        assertThat(stub.dialogVisible).isFalse()
        assertThat(stub.savedLocale.locale).isEqualTo(AppLocale.French.locale)

    }

    @Test
    fun `test selected locale - dialog value getter`() = runTest(UnconfinedTestDispatcher()){
        every { secureStorage.getStringValue(SecureStorage.locale) } returns AppLocale.English.locale
        val stub = createSut()

        //initially should be null
        assertThat(stub.selectedLocale).isEqualTo(null)
        //empty
        assertThat(stub.dialogLanguage).isEqualTo(UiText.StringValue(""))

        //select language
        stub.selectLocale(stub.languages.first { it.locale == AppLocale.French })

        assertThat((stub.dialogLanguage as UiText.StringResource).resourceId).isEqualTo(
            stub.languages.first { it.locale == AppLocale.French }.text
        )
        stub.confirmLocaleChange()

        stub.selectLocale(stub.languages.first { it.locale == AppLocale.English })
        assertThat((stub.dialogLanguage as UiText.StringResource).resourceId).isEqualTo(
            stub.languages.first { it.locale == AppLocale.English }.text
        )
    }





}
