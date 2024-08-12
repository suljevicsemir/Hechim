package com.semirsuljevic.foundation.api.authentication

interface CredentialsValidator {

    fun validatePassword(password: String): Boolean

    fun validatePasswords(password: String, confirmPassword: String): Boolean

    fun validateEmail(email: String): Boolean


}
