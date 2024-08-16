package com.semirsuljevic.foundation.internal.common

import com.semirsuljevic.foundation.api.common.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class DispatchersImpl @Inject constructor() : Dispatchers {
    override val default: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
    override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    override val unconfined: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
}
