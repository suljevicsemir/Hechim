import com.semirsuljevic.gradleplugins.HechimBrandFlavor
import com.semirsuljevic.gradleplugins.HechimFlavorDimension

plugins {
    alias(libs.plugins.convention.android.application)
    alias(libs.plugins.convention.android.application.compose)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.googleServices)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.hechimdemo"
    compileSdk = 34

    defaultConfig {

        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

//    signingConfigs {
//        create(SigningConstants.devFlavor) {
//            storeFile = file(SigningConstants.devStoreFile)
//            storePassword = keystoreProperties.getProperty(SigningConstants.devStorePassword)
//            keyAlias = keystoreProperties.getProperty(SigningConstants.devKeyAlias)
//            keyPassword = keystoreProperties.getProperty(SigningConstants.devKeyPassword)
//        }
//        create(SigningConstants.prodFlavor) {
//            storeFile = file(SigningConstants.prodStoreFile)
//            storePassword = keystoreProperties.getProperty(SigningConstants.prodStorePassword)
//            keyAlias = keystoreProperties.getProperty(SigningConstants.prodKeyAlias)
//            keyPassword = keystoreProperties.getProperty(SigningConstants.prodKeyPassword)
//        }
//    }


    flavorDimensions += HechimFlavorDimension.version.name
    productFlavors {
        HechimBrandFlavor.values().forEach {
            create(it.name) {
                dimension = it.dimension.name
                applicationId = it.applicationId
            }
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(project(":Dashboard"))
    implementation(project(":Foundation"))
    implementation(project(":Onboarding"))
    implementation(project(":UI"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
