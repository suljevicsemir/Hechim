package com.semirsuljevic.foundation.api.common

data class HechimError(
    val message: String,
    val buttonTitle: String = "",
    val raw: String = "",
    val code: Int = 400,
)
