package com.ferdialif.testapplication.core.navigation

sealed class Destination(val route:String) {
    object HomeScreen : Destination("home_screen")
    object DetailScreen : Destination("detail_screen")
}