package com.abdosharaf.arviewer

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdosharaf.arviewer.screens.AppScreen
import com.abdosharaf.arviewer.screens.MainScreen
import com.abdosharaf.arviewer.screens.ModelsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.ARScreen.route,
        enterTransition = {
            fadeIn(animationSpec = SpringSpec(dampingRatio = 0.6753f, stiffness = 100f))
        },
        exitTransition = {
            fadeOut(animationSpec = SpringSpec(dampingRatio = 0.6753f, stiffness = 100f))
        }
    ) {
        composable(route = AppScreen.ARScreen.route) { entry ->
            val modelPath = entry.arguments?.getString("model") ?: "plant_in_pot"

            MainScreen(model = modelPath, onButtonClicked = {
                navController.navigate(AppScreen.ModelsScreen.route)
            })
        }

        composable(route = AppScreen.ModelsScreen.route) {
            ModelsScreen(models = models, onItemClicked = { modelPath ->
                navController.navigate(AppScreen.ARScreen.route.replace("{model}", modelPath)) {
                    this.popUpTo(AppScreen.ARScreen.route) {
                        this.inclusive = true
                    }
                }
            })
        }
    }
}