package com.ferdialif.testapplication.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferdialif.testapplication.presentation.detail.DetailScreen
import com.ferdialif.testapplication.presentation.detail.DetailViewModel
import com.ferdialif.testapplication.presentation.main.MainScreen
import com.ferdialif.testapplication.presentation.main.MainViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val controller = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = Destination.HomeScreen.route
    ) {
        composable(route = Destination.HomeScreen.route) {
            val viewModel: MainViewModel = hiltViewModel()
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                navigateToDetailScreen = {
                    controller.navigate(Destination.DetailScreen.route+"/$it")
                }
            )
        }
        composable(route = Destination.DetailScreen.route+"/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )) {
            val viewModel: DetailViewModel = hiltViewModel()
            val id = it.arguments?.getInt("id") ?: 0
            DetailScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    controller.popBackStack()
                },
                viewModel = viewModel,
                id = id
            )
        }

    }
}