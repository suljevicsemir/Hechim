package com.semirsuljevic.foundation.internal.common

import com.semirsuljevic.foundation.api.common.serialiazers.HechimSerializers
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HechimSerializersImpl @Inject constructor(): HechimSerializers{
    override val json: Json = Json { ignoreUnknownKeys = true }
}
