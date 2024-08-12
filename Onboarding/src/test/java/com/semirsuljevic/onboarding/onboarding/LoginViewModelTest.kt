@file:OptIn(ExperimentalCoroutinesApi::class)

package com.semirsuljevic.onboarding.onboarding

import com.google.common.truth.Truth.assertThat
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginViewModel
import com.semirsuljevic.onboarding.onboarding.util.BaseMockkTest
import com.semirsuljevic.ui.api.navigation.Navigator
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class LoginViewModelTest:BaseMockkTest<LoginViewModel>() {

    @MockK
    private lateinit var navigator: Navigator
    @MockK
    private lateinit var hechimAuthentication: HechimAuthentication
    @MockK
    private lateinit var profile:Profile
    @MockK
    private lateinit var profileProvider: ProfileProvider

    override fun createSut(): LoginViewModel = LoginViewModel(navigator, hechimAuthentication, profile, profileProvider)

    override fun setUp() {}

    @Test
    fun `test credentials setter - email and password`() = runTest(UnconfinedTestDispatcher()){
        val email = "suljevic.semir@gmail.com"
        val password = "Hechim123!"
        val stub = createSut()
        assertThat(stub.email).isEmpty()
        stub.setEmail(email)
        assertThat(stub.email).isEqualTo(email)

        assertThat(stub.password).isEmpty()
        stub.onPasswordChange(password)
        assertThat(stub.password).isEqualTo(password)

        stub.setEmail("")
        assertThat(stub.email).isEmpty()
        stub.onPasswordChange("")
        assertThat(stub.password).isEmpty()
    }

    @Test
    fun `test - unsuccessful login`() = runTest(UnconfinedTestDispatcher()){
        val email = "suljevic.semir@gmail.com"
        val password = "Hechim123!"
        val error = HechimError(
            message = "Error occurred"
        )
        coEvery { hechimAuthentication.login(
            email, password
        ) } returns HechimResource.Error(error = error)

        val stub = createSut()
        stub.setEmail(email)
        stub.onPasswordChange(password)

        //unsuccessful login
        stub.login({})
        advanceUntilIdle()
        assertThat(stub.resource).isInstanceOf(HechimResource.Error::class.java)
        assertThat((stub.resource as HechimResource.Error).error.message).isEqualTo(error.message)

        //error UI should appear, test reset state button
        stub.resetState()
        assertThat(stub.resource).isInstanceOf(HechimResource.Nothing::class.java)
        assertThat((stub.resource as HechimResource.Nothing).message).isEmpty()

    }
}
