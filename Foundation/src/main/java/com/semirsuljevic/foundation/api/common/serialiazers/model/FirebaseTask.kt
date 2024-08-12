package com.semirsuljevic.foundation.api.common.serialiazers.model

import com.google.firebase.Firebase
import com.semirsuljevic.foundation.api.common.HechimError

sealed class FirebaseTask<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : FirebaseTask<T>(data = data, message = null)
    class Error<T>(message: String, data: T? = null) : FirebaseTask<T>(data = data, message = message)
}

