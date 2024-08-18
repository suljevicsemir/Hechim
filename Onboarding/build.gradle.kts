
plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.hechimdemo.onboarding"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(":Foundation"))
    implementation(project(":UI"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.security.crypto)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)
    implementation(libs.firebase.firestore)


    testImplementation(libs.mockk)
    testImplementation(libs.mockAndroid)
    testImplementation(libs.truth)
    testImplementation(libs.testCoroutine)
}
