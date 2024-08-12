package com.semirsuljevic.foundation

import com.semirsuljevic.foundation.internal.authentication.CredentialsValidatorImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

internal class CredentialsValidatorTest {
    private fun createStub() = CredentialsValidatorImpl()

    @Test
    fun passwordValidation() {
        val stub = createStub()
        //correct password
        assert(stub.validatePassword("Hechim123!"))
        //missing digit
        assertFalse(stub.validatePassword("Hechim**!"))
        //missing lower case letter
        assertFalse(stub.validatePassword("HECHIM123!"))
        //missing upper case letter
        assertFalse(stub.validatePassword("hechim123"))
        //missing special character
        assertFalse(stub.validatePassword("Hechim12345"))
        //present whitespace
        assertFalse(stub.validatePassword("Hechim 123!"))
        //length less than 8
        assertFalse(stub.validatePassword("Hec123!"))
    }

    @Test
    fun bothPasswordsValidation() {
        val stub = createStub()
        //correct password
        assert(stub.validatePasswords(
            "Hechim123!",
            "Hechim123!"
        ))
        assert(stub.validatePasswords(
            "Hechim123!",
            "Hechim1234!"
        ))
        assert(stub.validatePasswords(
            "Hechim123!",
            "Aechim1234!"
        ))

    }


    @Test
    fun validateEmail() {
        val stub = createStub()
        assert(stub.validateEmail("suljevic.semir@gmail.com"))
        assertFalse(stub.validateEmail("suljevic.semirgmail.com"))
        assertFalse(stub.validateEmail("suljevic.semir@gmail"))
    }
}
