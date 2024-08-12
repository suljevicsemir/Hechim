plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "hechimdemo.android.application"
            implementationClass = "ApplicationPlugin"
        }
        register("androidApplicationCompose") {
            id = "hechimdemo.android.application.compose"
            implementationClass = "ApplicationComposePlugin"
        }
        register("androidFeature") {
            id = "hechimdemo.android.feature"
            implementationClass = "FeaturePlugin"
        }
        register("androidLibraryCompose") {
            id = "hechimdemo.android.library.compose"
            implementationClass = "LibraryComposePlugin"
        }

        register("androidLibrary") {
            id = "hechimdemo.android.library"
            implementationClass = "LibraryPlugin"
        }
        register("androidHilt") {
            id = "hechimdemo.android.hilt"
            implementationClass = "HiltPlugin"
        }
        register("androidBuildType") {
            id = "hechimdemo.android.buildtype"
            implementationClass = "BuildTypePlugin"
        }
        register("androidProjectFlavor") {
            id = "hechimdemo.android.ProjectFlavor"
            implementationClass = "ProjectFlavorPlugin"
        }
    }
}
