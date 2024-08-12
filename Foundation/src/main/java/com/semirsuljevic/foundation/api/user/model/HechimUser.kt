package com.semirsuljevic.foundation.api.user.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class HechimUser(
    var id: String = "",
    var email: String = "",
    @get:PropertyName("first_name") @set:PropertyName("first_name") var firstName: String = "",
    @get:PropertyName("last_name")  @set:PropertyName("last_name" ) var lastName: String = ""
) {

    val completedName get() = firstName.isNotEmpty() && lastName.isNotEmpty()
}
