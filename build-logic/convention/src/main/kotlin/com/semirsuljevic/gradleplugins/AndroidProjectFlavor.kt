package com.semirsuljevic.gradleplugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureProjectFlavor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += HechimFlavorDimension.version.name
        productFlavors {
            HechimBrandFlavor.values().forEach {
                create(it.name){
                    dimension = it.dimension.name
                }
            }
        }
    }
}
