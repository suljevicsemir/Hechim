package com.semirsuljevic.foundation.internal.authentication

import androidx.core.util.PatternsCompat
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import java.util.regex.Pattern
import javax.inject.Inject

class CredentialsValidatorImpl @Inject constructor(): CredentialsValidator {

    private val pattern : Pattern = Pattern.compile("^" +
        "(?=.*[0-9])" +         //at least 1 digit
        "(?=.*[a-z])" +         //at least 1 lower case letter
        "(?=.*[A-Z])" +         //at least 1 upper case letter
        "(?=.*[@#$%^&+=!])" +    //at least 1 special character
        "(?=\\S+$)" +           //no white spaces
        ".{8,}" +               //at least 8 characters
        "$")

    override fun validatePassword(password: String) = pattern.matcher(password).matches()
    override fun validatePasswords(password: String, confirmPassword: String) =
        validatePassword(password) && password.compareTo(confirmPassword) == 0

    override fun validateEmail(email: String) = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}
