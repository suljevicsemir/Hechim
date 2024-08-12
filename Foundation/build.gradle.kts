plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.hechimdemo.storage"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.security.crypto)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)
    implementation(libs.firebase.firestore)
}
