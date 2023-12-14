package com.example.diagnofish.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Store : Screen("store")
    object Scan : Screen("scan")
    object Article : Screen("article")
    object History : Screen("history")
}