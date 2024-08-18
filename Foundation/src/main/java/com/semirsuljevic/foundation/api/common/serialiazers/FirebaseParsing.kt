package com.semirsuljevic.foundation.api.common.serialiazers

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.serialiazers.model.FirebaseTask
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseParsing @Inject constructor(
    val serializers: HechimSerializers
){
    inline fun <reified T> convertJsonStringToObject(jsonString: String): T =
        serializers.json.decodeFromString(jsonString)

    fun encodeQuerySnapshot(querySnapshot: QuerySnapshot): String {
        val list = querySnapshot.documents.map {
            it.data
        }
        return serializers.json.encodeToString(list)
    }


    fun encodeDocumentSnapshot(snapshot: DocumentSnapshot): String =
        serializers.json.encodeToString(snapshot)


    fun <T> firebaseMiddleware(firebaseTask: FirebaseTask<T>): HechimResource<Any> {
        if(firebaseTask is FirebaseTask.Success) {
            return HechimResource.Success(
                data = firebaseTask.data!!
            )
        }
        return HechimResource.Error(
            error = HechimError(message = "")
        )
    }


    suspend inline fun <reified T> convertQuerySnapshot(task: Task<QuerySnapshot>): FirebaseTask<T> {
        task.await()
        if(!task.isSuccessful) {
            return FirebaseTask.Error(
                message = "Failed"
            )
        }
        val jsonString = encodeQuerySnapshot(task.result)
        return FirebaseTask.Success(
            data = convertJsonStringToObject<T>(jsonString)!!
        )
    }

    suspend inline fun <reified T> convertDocumentSnapshot(task: Task<DocumentSnapshot>): FirebaseTask<T> {
        task.await()
        if(!task.isSuccessful) {
            return FirebaseTask.Error(
                message = "Failed"
            )
        }
        val jsonString = encodeDocumentSnapshot(task.result)
        return FirebaseTask.Success(data = convertJsonStringToObject<T>(jsonString))
    }
}
