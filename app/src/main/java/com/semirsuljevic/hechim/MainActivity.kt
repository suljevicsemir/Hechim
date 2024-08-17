package com.semirsuljevic.hechim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.semirsuljevic.hechim.navigation.AppNavigator
import com.semirsuljevic.hechim.ui.theme.HechimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
