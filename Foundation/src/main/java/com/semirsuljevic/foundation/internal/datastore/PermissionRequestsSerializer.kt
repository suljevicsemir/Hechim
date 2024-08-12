package com.semirsuljevic.foundation.internal.datastore

import androidx.datastore.core.Serializer
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal object PermissionRequestsSerializer: Serializer<PermissionRequests> {
    override val defaultValue: PermissionRequests get() = PermissionRequests(finishedPermissions = false)

    override suspend fun readFrom(input: InputStream): PermissionRequests {
        return try {
            Json.decodeFromString(
                deserializer = PermissionRequests.serializer(),
                string = input.readBytes().decodeToString()
            )
        }
        catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: PermissionRequests, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = PermissionRequests.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
