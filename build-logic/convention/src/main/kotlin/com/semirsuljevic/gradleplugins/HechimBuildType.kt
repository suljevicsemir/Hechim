package com.semirsuljevic.gradleplugins

enum class HechimBuildType(val applicationIdSuffix:String? = null) {
    DEBUG(".debug"),
    BETA(".qa"),
    STAGING(".staging"),
    RELEASE
}
