
plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.convention.android.library.compose)
}

android {
    namespace = "com.semirsuljevic.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":Foundation"))
}
