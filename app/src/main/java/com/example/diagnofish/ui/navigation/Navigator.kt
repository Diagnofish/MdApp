package com.example.diagnofish.ui.navigation

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diagnofish.model.dummyArticleItems
import com.example.diagnofish.model.dummyHistoryItems
import com.example.diagnofish.repository.UserPreferencesRepository
import com.example.diagnofish.screens.ArticleDetailScreen
import com.example.diagnofish.screens.LoginScreen
import com.example.diagnofish.screens.MainScreen
import com.example.diagnofish.screens.RegisterScreen
import com.example.diagnofish.screens.ScanDetailScreen
import com.example.diagnofish.screens.UserProfileScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun Navigator() {
    val navHostController = rememberNavController()
    val isLoggedIn by UserPreferencesRepository(LocalContext.current).isLoggedIn().collectAsState(initial = false)
    NavHost(navController = navHostController, startDestination = if (isLoggedIn) Screen.Main.route else Screen.Auth.route) {
        navigation(route = Screen.Auth.route, startDestination = Screen.Auth.Register.route) {
            composable(Screen.Auth.Register.route) {
                RegisterScreen(navController = navHostController)
            }
            composable(Screen.Auth.Login.route) {
                LoginScreen(navController = navHostController)
            }
        }
        navigation(route = Screen.Main.route, startDestination = Screen.Main.Home.route) {
            composable(Screen.Main.Home.route) {
                MainScreen(navController = navHostController, route = Screen.Main.Home.route)
            }
            composable(Screen.Main.Store.route) {
                MainScreen(navController = navHostController, route = Screen.Main.Store.route)
            }
            composable(Screen.Main.Scan.route) {
                MainScreen(navController = navHostController, route = Screen.Main.Scan.route)
            }
            composable(Screen.Main.Article.route) {
                MainScreen(navController = navHostController, route = Screen.Main.Article.route)
            }
            composable(Screen.Main.History.route) {
                MainScreen(navController = navHostController, route = Screen.Main.History.route)
            }
        }
        composable(route = Screen.ScanDetail.route + "?id={id}", arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) {
            val index = it.arguments?.getString("id") ?: "0"
            ScanDetailScreen(navController = navHostController, id = index)
        }
        composable(route = Screen.ArticleDetail.route + "?id={id}", arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) {
            val index = it.arguments?.getString("id") ?: "0"
            ArticleDetailScreen(navController = navHostController, articleItem = dummyArticleItems[index.toInt()])
        }
        composable(route = Screen.UserProfile.route) {
            UserProfileScreen(navController = navHostController)
        }
    }
}
