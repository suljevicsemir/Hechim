@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.semirsuljevic.onboarding.onboarding.register

import com.google.common.truth.Truth.assertThat
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.onboarding.api.welcome.viewmodel.RegisterViewModel
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

internal class RegisterViewModelTest: BaseMockkTest<RegisterViewModel>(){


    @MockK
    private lateinit var navigator: Navigator
    @MockK
    private lateinit var authentication: HechimAuthentication
    @MockK
    private lateinit var credentialsValidator: CredentialsValidator

    override fun createSut(): RegisterViewModel = RegisterViewModel(
        navigator, authentication, credentialsValidator)

    override fun setUp() {}

    @Test
    fun `credentials setters `() = runTest(UnconfinedTestDispatcher()){
        val password = "Hechim123!"
        val confirmPassword = "Hechim1234!"

        val stub = createSut()
        assertThat(stub.password).isEmpty()
        assertThat(stub.confirmPassword).isEmpty()

        stub.setConfirmPassword(confirmPassword)
        stub.setPassword(password)
        assertThat(stub.password).isEqualTo(password)
        assertThat(stub.confirmPassword).isEqualTo(confirmPassword)
    }

    @Test
    fun `reset state - after loading or error state should change to nothing`() = runTest(UnconfinedTestDispatcher()){
        val email = "suljevic.semir@gmail.com"
        val password = "Hechim123!"

        coEvery { authentication.register(email, password
        ) } returns HechimResource.Success(data = HechimUser("id", email))
        every { credentialsValidator.validatePasswords(password, password) } returns true

        val stub = createSut()
        assertThat(stub.resource).isInstanceOf(HechimResource.Nothing::class.java)

        //set credentials
        stub.setEmail(email)
        stub.setPassword(password)
        stub.setConfirmPassword(password)

        stub.register()
        //after setting the call, before completed, resource should be in loading state
        assertThat(stub.resource).isInstanceOf(HechimResource.Loading::class.java)
        advanceUntilIdle()
        //success call (or error, makes no difference) sets resource to another instance
        assertThat(stub.resource).isInstanceOf(HechimResource.Success::class.java)

        stub.resetState()
        assertThat(stub.resource).isInstanceOf(HechimResource.Nothing::class.java)
    }
}
