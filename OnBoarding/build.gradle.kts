
plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
}

android {
    namespace = "com.semirsuljevic.onboarding"


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
