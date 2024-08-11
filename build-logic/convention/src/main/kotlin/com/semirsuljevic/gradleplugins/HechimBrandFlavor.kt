package com.semirsuljevic.gradleplugins

enum class HechimBrandFlavor (val dimension: HechimFlavorDimension, val applicationId: String? = null) {
    DEV(HechimFlavorDimension.version,"com.semirsuljevic.hechim.dev"),
    PROD(HechimFlavorDimension.version,"ba.semirsuljevic.hechim")
}
