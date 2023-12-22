package com.example.diagnofish.ui.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth") {
        object Login : Screen("login")
        object Register : Screen("register")
    }

    object Main : Screen("main") {
        object Home : Screen("home")
        object Store : Screen("store")
        object Scan : Screen("scan")
        object Article : Screen("article")
        object History : Screen("history")
    }

    object ScanDetail : Screen("scan_detail")
    object ArticleDetail : Screen("article_detail")
    object UserProfile : Screen("user_profile")
}