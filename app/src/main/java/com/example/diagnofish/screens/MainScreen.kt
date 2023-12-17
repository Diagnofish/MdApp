package com.example.diagnofish.screens

import ButtonWithIcon
import LinkText
import ScreenTitle
import SectionTitle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diagnofish.R
import com.example.diagnofish.model.dummyArticleItems
import com.example.diagnofish.model.dummyHistoryItems
import com.example.diagnofish.ui.components.BottomBar
import com.example.diagnofish.ui.components.CardArticle
import com.example.diagnofish.ui.components.CardHistorySmall
import com.example.diagnofish.ui.components.Header
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.Light
import com.example.diagnofish.ui.theme.Lighter
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.Secondary
import com.example.diagnofish.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var title by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ScreenTitle(
                        text = title
                    )
                },
                actions = {
                    Image(painter = painterResource(id = R.drawable.icon_user), contentDescription = stringResource(
                        id = R.string.app_name)
                    )
                },
                modifier = Modifier
                    .padding(0.dp)
                    .shadow(8.dp)
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Scan.route, modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) {
                title = stringResource(id = R.string.greeting)
                HomeScreen()
            }
            composable(Screen.Store.route) {
                title = stringResource(id = R.string.nav_store)
                StoreScreen()
            }
            composable(Screen.Scan.route) {
                title = stringResource(id = R.string.fish_detection)
                ScanScreen()
            }
            composable(Screen.Article.route) {
                title = stringResource(id = R.string.nav_articles)
                ArticleScreen()
            }
            composable(Screen.History.route) {
                title = stringResource(id = R.string.detection_history)
                HistoryScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Secondary)
    ) {
        Header(modifier = Modifier.padding(16.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
            .background(Lighter)
            .padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {
                SectionTitle(text = stringResource(id = R.string.detection_history), fontSize = 18.sp)
                LinkText(text = stringResource(id = R.string.see_more), color = Primary, onClick = {})
            }
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                ) {
                items(dummyHistoryItems.size) {
                    CardHistorySmall(historyItem = dummyHistoryItems[it])
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)) {
                SectionTitle(text = stringResource(id = R.string.articles), fontSize = 18.sp)
                LinkText(text = stringResource(id = R.string.see_more), color = Primary, onClick = {})
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dummyArticleItems.size) {
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    CardArticle(articleItem = dummyArticleItems[it])
                }
            }

        }
    }
}

@Composable
fun StoreScreen() {
    Column {
        Text(text = stringResource(id = R.string.nav_store))
    }
}

@Composable
fun ScanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Light)
                .padding(vertical = 16.dp)) {
                Image(painter = painterResource(id = R.drawable.fish), contentDescription = "Scan Fish", modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))
                Text(text = stringResource(id = R.string.photo_text), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 22.dp), textAlign = TextAlign.Center, fontSize = 17.sp, fontFamily = FontFamily(Font(R.font.inter_medium)), fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    ButtonWithIcon(icon = painterResource(id = R.drawable.icon_gallery), text = stringResource(id = R.string.gallery), onClick = {})
                    ButtonWithIcon(icon = painterResource(id = R.drawable.icon_camera), text = stringResource(id = R.string.take_photo), onClick = {})
                }
            }
        }
    }
}

@Composable
fun ArticleScreen() {
    Column {
        Text(text = stringResource(id = R.string.nav_articles))
    }
}

@Composable
fun HistoryScreen() {
    Column {
        Text(text = stringResource(id = R.string.nav_history))
    }
}