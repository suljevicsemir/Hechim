package com.semirsuljevic.onboarding.onboarding.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.pager.PagerState
import com.google.common.truth.Truth.assertThat
import com.semirsuljevic.foundation.api.secure.SecureStorage
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.onboarding.api.welcome.viewmodel.OnBoardingViewModel
import com.semirsuljevic.onboarding.onboarding.util.BaseMockkTest
import com.semirsuljevic.ui.api.navigation.Navigator
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalFoundationApi::class)
class OnBoardingViewModelTest: BaseMockkTest<OnBoardingViewModel>() {

    @MockK
    private lateinit var navigator: Navigator


    override fun createSut(): OnBoardingViewModel = OnBoardingViewModel(navigator)

    override fun setUp() {
        TODO("Not yet implemented")
    }

    @Test
    fun `test selected index - user gesture`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.setIndex(1)
        //test correct setting
        assertThat(stub.selectedIndex).isEqualTo(1)
        //test incorrect setting index - no more screens
        stub.setIndex(stub.items.size + 2)
        //wouldn't be changed from the previous call
        assertThat(stub.selectedIndex).isEqualTo(1)
    }

    @Test
    fun `test last screen flag`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        assertThat(stub.onLastScreen).isFalse()
        //set index to last item
        stub.setIndex(stub.items.size - 1)
        assertThat(stub.onLastScreen).isTrue()

    }

    @Test
    fun `test pagerItem - getter for currently selected view`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        val firstItem = stub.pagerItem(0)
        assertThat(firstItem.image).isEqualTo(stub.items[0].image)
        val secondItem = stub.pagerItem(1)
        assertThat(secondItem.image).isEqualTo(stub.items[1].image)
    }
}
