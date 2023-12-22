package com.example.diagnofish.ui.components

import BasicText
import StatusBadge
import TextButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.diagnofish.R
import com.example.diagnofish.model.ArticleItem
import com.example.diagnofish.model.DetectionHistory
import com.example.diagnofish.model.HistoryItem
import com.example.diagnofish.model.dummyArticleItems
import com.example.diagnofish.model.dummyHistoryItems
import com.example.diagnofish.ui.navigation.NavigationItem
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.Light
import com.example.diagnofish.ui.theme.Lighter
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.TextDark

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
                screen = Screen.Main.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_store),
                icon = ImageVector.vectorResource(id = R.drawable.icon_store),
                screen = Screen.Main.Store
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_scan),
                icon = ImageVector.vectorResource(id = R.drawable.icon_scan),
                screen = Screen.Main.Scan
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_articles),
                icon = ImageVector.vectorResource(id = R.drawable.icon_article),
                screen = Screen.Main.Article
            ),
            NavigationItem(
                title = stringResource(id = R.string.nav_history),
                icon = ImageVector.vectorResource(id = R.drawable.icon_history),
                screen = Screen.Main.History
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
                        popUpTo(Screen.Main.Home.route) {
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

@Preview
@Composable
fun Header(modifier: Modifier = Modifier, action: () -> Unit = {}) {
    Column(
        modifier
            .fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            BasicText(text = stringResource(id = R.string.banner_text), modifier = Modifier.weight(1f), lineHeight = 24.sp)
            Image(painter = painterResource(R.drawable.scan_fish), contentDescription = "Scan Fish", modifier = Modifier.size(80.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(20))
            .background(Lighter)
            .padding(16.dp)
        ) {
            BasicText(text = stringResource(id = R.string.detection_text), modifier = Modifier.weight(1f), fontSize = 14.sp, lineHeight = 24.sp)
            TextButton(shadowElevation = 0.dp, shape = RoundedCornerShape(50), text = stringResource(id = R.string.scan), onClick = action)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHistorySmall(detectionHistory: DetectionHistory, onClick: () -> Unit = {}) {
    ElevatedCard(modifier = Modifier
        .width(160.dp)
        .background(Light)
        .shadow(8.dp),
        onClick = onClick
    ) {
    Column (
        Modifier.padding(16.dp)) {
        BasicText(text = detectionHistory.created_at.substringBefore("T"), fontSize = 14.sp, lineHeight = 24.sp)
        BasicText(text = detectionHistory.fish_name, fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicText(text = stringResource(id = R.string.show_status) + " ")
            StatusBadge(if (detectionHistory.confidence_score < 0.6)
                "Gagal"
            else if (detectionHistory.result.equals(stringResource(R.string.status_healthy))) "Sehat" else "Terinfeksi")
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CardArticle(articleItem: ArticleItem = dummyArticleItems[0], onClick: () -> Unit = {}) {
    ElevatedCard(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Row(modifier = Modifier
            .background(Light)
            .padding(8.dp)) {
            Image(painter = painterResource(id = articleItem.image), contentDescription = articleItem.title, modifier = Modifier
                .height(100.dp)
                .clip(RoundedCornerShape(10)))
            BasicText(text = articleItem.title, fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 16.dp, start = 8.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHistory(detectionHistory: DetectionHistory, onClick: () -> Unit = {}) {
    ElevatedCard(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Row(modifier = Modifier
            .background(Light)
            .padding(8.dp)) {
            AsyncImage(
                model = "https://storage.googleapis.com/diagnofish/detection-images/${detectionHistory.image_filename}", contentDescription = detectionHistory.fish_name,
                fallback = painterResource(id = R.drawable.blank_image),
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(10))
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                BasicText(
                    text = detectionHistory.created_at.substringBefore("T"),
                    fontSize = 14.sp,
                    lineHeight = 24.sp
                )
                BasicText(
                    text = detectionHistory.fish_name,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicText(text = stringResource(id = R.string.show_status) + " ")
                    StatusBadge(
                        if (detectionHistory.confidence_score < 0.6)
                            "Gagal"
                        else if(detectionHistory.result.equals(stringResource(R.string.status_healthy)))
                            "Sehat"
                        else
                            "Terinfeksi")
                }
            }
        }
    }
}