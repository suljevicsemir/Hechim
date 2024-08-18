package com.semirsuljevic.onboarding.onboarding

import com.google.common.truth.Truth.assertThat
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.secure.SecureStorage
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginEmailViewModel
import com.semirsuljevic.onboarding.onboarding.util.BaseMockkTest
import com.semirsuljevic.ui.api.navigation.Navigator
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoginEmailViewModelTest: BaseMockkTest<LoginEmailViewModel>(){


    @MockK
    private lateinit var navigator: Navigator

    @MockK
    private lateinit var hechimAuthentication: HechimAuthentication
    override fun createSut(): LoginEmailViewModel = LoginEmailViewModel(
        navigator, hechimAuthentication
    )

    override fun setUp() {
        TODO("Not yet implemented")
    }

    @Test
    fun `test email setter`() = runTest(UnconfinedTestDispatcher()){
        coEvery { hechimAuthentication.checkEmail("suljevic.semir@gmail.com") } returns true
        val stub = createSut()
        assertThat(stub.email).isEmpty()
        stub.onEmailChange("suljevic.semir@gmail.com")
        assertThat(stub.email).isEqualTo("suljevic.semir@gmail.com")



    }

    @Test
    fun `test email exists`() = runTest(UnconfinedTestDispatcher()){
        coEvery { hechimAuthentication.checkEmail("suljevic.semir@gmail.com") } returns true
        val stub = createSut()
        assertThat(stub.email).isEmpty()
        stub.onEmailChange("suljevic.semir@gmail.com")
        assertThat(stub.email).isEqualTo("suljevic.semir@gmail.com")

        stub.checkEmail()
        assertThat(stub.resource).isInstanceOf(HechimResource.Loading::class.java)
        advanceUntilIdle()
        assertThat(stub.resource).isInstanceOf(HechimResource.Nothing::class.java)

    }

    @Test
    fun `test email doesn't exist`() = runTest(UnconfinedTestDispatcher()){
        coEvery { hechimAuthentication.checkEmail("suljevic.semir@gmail.com") } returns false
        val stub = createSut()
        assertThat(stub.email).isEmpty()
        stub.onEmailChange("suljevic.semir@gmail.com")
        assertThat(stub.email).isEqualTo("suljevic.semir@gmail.com")

        stub.checkEmail()
        assertThat(stub.resource).isInstanceOf(HechimResource.Loading::class.java)
        assertThat((stub.resource as HechimResource.Loading).message).isEqualTo("Checking your email")
        advanceUntilIdle()
        assertThat(stub.resource).isInstanceOf(HechimResource.Nothing::class.java)
        assertThat((stub.resource as HechimResource.Nothing).message).isEqualTo("")

    }
}
