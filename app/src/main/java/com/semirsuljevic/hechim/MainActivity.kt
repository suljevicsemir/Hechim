package com.semirsuljevic.hechim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerServiceManager
import com.semirsuljevic.foundation.api.sdk.config.TrackerServiceConstants
import com.semirsuljevic.hechim.navigation.AppNavigator
import com.semirsuljevic.hechim.ui.theme.HechimTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var trackerNotificationManager: TrackerNotificationManager
    @Inject
    lateinit var trackerServiceManager: TrackerServiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackerNotificationManager.createChannel()
        enableEdgeToEdge()
        //check permissions and start
        if((intent.action ?: "").compareTo(TrackerServiceConstants.START_WORKOUT) == 0) {
            trackerServiceManager.startService()
        }
        setContent {
            HechimTheme {
                val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {}
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = com.semirsuljevic.ui.api.theme.HechimTheme.colors.backgroundDefault
                ) {
                    AppNavigator(viewModelStoreOwner = viewModelStoreOwner)
                }
            }
        }
    }
}
