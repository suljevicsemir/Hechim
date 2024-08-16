package com.semirsuljevic.foundation.api.common

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
