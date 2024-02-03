package com.ferdialif.testapplication.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferdialif.testapplication.presentation.detail.DetailScreen
import com.ferdialif.testapplication.presentation.main.MainScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Destination.HomeScreen.route) {
        composable(route = Destination.HomeScreen.route) {
            MainScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Destination.DetailScreen.route) {
            DetailScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    controller.popBackStack()
                }
            )
        }

    }
}