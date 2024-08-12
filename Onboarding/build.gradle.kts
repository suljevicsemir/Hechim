
plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
    //alias(libs.plugins.kotlin.serialization)
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

    testImplementation(libs.mockk)
    testImplementation(libs.mockAndroid)
    testImplementation(libs.truth)
    testImplementation(libs.testCoroutine)
}
