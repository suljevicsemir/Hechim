package com.semirsuljevic.gradleplugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
//        buildTypes {
//            create("beta") {
//                initWith(getByName("debug"))
//                isMinifyEnabled = false
//                isJniDebuggable = false
//            }
//            create("staging") {
//                initWith(getByName("debug"))
//                isMinifyEnabled = false
//                isJniDebuggable = false
//            }
//        }
    }
}
