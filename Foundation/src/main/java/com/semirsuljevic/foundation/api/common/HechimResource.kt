package com.semirsuljevic.foundation.api.common

sealed interface HechimResource <out T : Any>{
    data class Success<T: Any>(val data: T) : HechimResource<T>
    data class Error<T: Any>(val error: HechimError) : HechimResource<T>
    data class Loading<T: Any>(val message: String? = null): HechimResource<T>
    data class Nothing<T:Any>(val message: String = ""): HechimResource<T>
}
