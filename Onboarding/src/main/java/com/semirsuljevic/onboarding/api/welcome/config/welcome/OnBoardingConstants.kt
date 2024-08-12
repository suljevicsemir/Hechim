package com.semirsuljevic.onboarding.api.welcome.config.welcome

import com.hechimdemo.onboarding.R

object OnBoardingConstants {

    val onBoardingItems get() = listOf(
        firstOnBoardingScreen,
        secondOnBoardingScreen,
        thirdOnBoardingScreen
    )

    private val firstOnBoardingScreen = OnBoardingItem(
        headlineKey = R.string.onboarding_1_headline,
        descriptionKey = R.string.onboarding_1_desc,
        image = R.drawable.img_onboarding_1
    )

    private val secondOnBoardingScreen =  OnBoardingItem(
        headlineKey = R.string.onboarding_2_headline,
        descriptionKey = R.string.onboarding_2_desc,
        image = R.drawable.img_onboarding_2
    )

    private val thirdOnBoardingScreen = OnBoardingItem(
        headlineKey = R.string.onboarding_2_headline,
        descriptionKey = R.string.onboarding_2_desc,
        image = R.drawable.img_onboarding_3
    )
}
