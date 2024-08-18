plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.semirsuljevic.dashboard"
}

dependencies {
    implementation(project(":UI"))
    implementation(project(":Foundation"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
