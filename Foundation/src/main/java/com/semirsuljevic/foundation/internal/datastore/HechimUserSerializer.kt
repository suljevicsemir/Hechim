package com.semirsuljevic.foundation.internal.datastore

import androidx.datastore.core.Serializer
import com.semirsuljevic.foundation.api.user.model.HechimUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal object HechimUserSerializer: Serializer<HechimUser> {
    override val defaultValue: HechimUser get() = HechimUser()

    override suspend fun readFrom(input: InputStream): HechimUser {
        return try {
            Json.decodeFromString(
                deserializer = HechimUser.serializer(),
                string = input.readBytes().decodeToString()
            )
        }
        catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: HechimUser, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = HechimUser.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
