package com.abdosharaf.arviewer.screens

import com.abdosharaf.arviewer.screens.Routes.AR_SCREEN_ROUTE
import com.abdosharaf.arviewer.screens.Routes.MODELS_SCREEN_ROUTE

sealed class AppScreen(val route: String) {
    data object ARScreen: AppScreen(route = AR_SCREEN_ROUTE)
    data object ModelsScreen: AppScreen(route = MODELS_SCREEN_ROUTE)
}