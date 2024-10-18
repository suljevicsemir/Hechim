plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp.plugin)
}

android {
    namespace = "com.hechimdemo.storage"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(libs.room)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.security.crypto)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)
    implementation(libs.firebase.firestore)
    implementation(libs.datastore.preferences)
    implementation(libs.media)
}
