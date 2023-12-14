package com.example.diagnofish.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.diagnofish.R
import com.example.diagnofish.ui.navigation.NavigationItem
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.greeting)
                    )
                },
                actions = {
                    Icon(painter = painterResource(id = R.drawable.icon_user), contentDescription = stringResource(
                        id = R.string.app_name)
                    )
                }
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Store.route) {
                StoreScreen()
            }
            composable(Screen.Scan.route) {
                ScanScreen()
            }
            composable(Screen.Article.route) {
                ArticleScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.nav_home),
                icon = ImageVector.vectorResource(id = R.drawable.icon_home),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_store),
                icon = ImageVector.vectorResource(id = R.drawable.icon_store),
                screen = Screen.Store
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_scan),
                icon = ImageVector.vectorResource(id = R.drawable.icon_scan),
                screen = Screen.Scan
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_articles),
                icon = ImageVector.vectorResource(id = R.drawable.icon_article),
                screen = Screen.Article
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_history),
                icon = ImageVector.vectorResource(id = R.drawable.icon_history),
                screen = Screen.History
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(64.dp),
                        tint = if (currentRoute == item.screen.route) Primary else TextDark
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Column() {
        Text(text = stringResource(id = R.string.nav_home))
    }
}

@Composable
fun StoreScreen() {
    Column() {
        Text(text = stringResource(id = R.string.nav_store))
    }
}

@Composable
fun ScanScreen() {
    Column() {
        Text(text = stringResource(id = R.string.nav_scan))
    }
}

@Composable
fun ArticleScreen() {
    Column() {
        Text(text = stringResource(id = R.string.nav_articles))
    }
}

@Composable
fun HistoryScreen() {
    Column() {
        Text(text = stringResource(id = R.string.nav_history))
    }
}

@Composable
fun App() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.fish), contentDescription = stringResource(
                id = R.string.app_name
            )
        )
        Text(buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = TextDark,
                    fontFamily = FontFamily(Font(R.font.inter))
                )
            ) {
                append(stringResource(id = R.string.welcome) + " ")
            }
            withStyle(
                style = SpanStyle(
                    color = Primary,
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                )
            ) {
                append(stringResource(id = R.string.app_name))
            }
        })
        Text(text = stringResource(id = R.string.login), Modifier.align(Alignment.Start))
        OutlinedTextField(value = "", onValueChange = {}, Modifier.padding(0.dp))
        OutlinedTextField(value = "", onValueChange = {})
    }
}
