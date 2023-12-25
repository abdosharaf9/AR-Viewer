package com.abdosharaf.arviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.abdosharaf.arviewer.ui.theme.MainColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = MainColor)
            systemUiController.setNavigationBarColor(color = MainColor)
            AppNavigation()
        }
    }
}

@Preview
@Composable
fun MainTest() {
    AppNavigation()
}