package com.example.diagnofish.screens

import BasicText
import ButtonWithIcon
import LinkText
import ScreenTitle
import SectionTitle
import TextButton
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.navigation
import com.canhub.cropper.CropImage.CancelledResult.uriContent
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.diagnofish.R
import com.example.diagnofish.model.DetectionHistory
import com.example.diagnofish.model.dummyArticleItems
import com.example.diagnofish.model.dummyHistoryItems
import com.example.diagnofish.repository.UserPreferencesRepository
import com.example.diagnofish.ui.components.BottomBar
import com.example.diagnofish.ui.components.CardArticle
import com.example.diagnofish.ui.components.CardHistory
import com.example.diagnofish.ui.components.CardHistorySmall
import com.example.diagnofish.ui.components.Header
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.Light
import com.example.diagnofish.ui.theme.Lighter
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.Secondary
import com.example.diagnofish.ui.theme.TextDanger
import com.example.diagnofish.ui.theme.TextDark
import com.example.diagnofish.util.Response
import com.example.diagnofish.util.UriUtil
import com.example.diagnofish.viewmodel.HistoryViewModel
import com.example.diagnofish.viewmodel.ScanViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.nio.file.Path

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    route: String = Screen.Main.Home.route,
) {
    var title by remember { mutableStateOf("") }
    val userPreferencesRepository = UserPreferencesRepository(LocalContext.current)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ScreenTitle(
                        text = title
                    )
                },
                actions = {
                    LinkText("Logout", onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            userPreferencesRepository.clear()
                        }
                    })
                    Image(modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screen.UserProfile.route)
                        }, painter = painterResource(id = R.drawable.icon_user), contentDescription = stringResource(
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
    ) {innerPadding ->
        when (route) {
            Screen.Main.Home.route -> {
                title = stringResource(id = R.string.greeting)
                HomeScreen(modifier = Modifier.padding(innerPadding), navController = navController)
            }
            Screen.Main.Store.route -> {
                title = stringResource(id = R.string.nav_store)
                StoreScreen(modifier = Modifier.padding(innerPadding))
            }
            Screen.Main.Scan.route -> {
                title = stringResource(id = R.string.fish_detection)
                ScanScreen(modifier = Modifier.padding(innerPadding), navController = navController)
            }
            Screen.Main.Article.route -> {
                title = stringResource(id = R.string.nav_articles)
                ArticleScreen(modifier = Modifier.padding(innerPadding), navController = navController)
            }
            Screen.Main.History.route -> {
                title = stringResource(id = R.string.detection_history)
                HistoryScreen(modifier = Modifier.padding(innerPadding), navController = navController)
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController, historyViewModel: HistoryViewModel = koinViewModel()) {
    fun launch() {
        historyViewModel.getHistory()
    }
    if (historyViewModel.result.value is Response.Empty) {
        launch()
    }
    Column(modifier = modifier
        .fillMaxSize()
        .background(Secondary)
    ) {
        Header(modifier = Modifier.padding(16.dp), action = {
            navController.navigate(Screen.Main.Scan.route) {
                popUpTo(Screen.Main.Home.route) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        })
        Column(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
            .background(Lighter)
            .padding(horizontal = 16.dp)) {
            if (historyViewModel.result.value is Response.Success) {
                val history = (historyViewModel.result.value as Response.Success<List<DetectionHistory>>).data
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)) {
                    SectionTitle(text = stringResource(id = R.string.detection_history), fontSize = 18.sp)
                    LinkText(text = stringResource(id = R.string.see_more), color = Primary, onClick = {
                        navController.navigate(Screen.Main.History.route) {
                            popUpTo(Screen.Main.Home.route) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                ) {
                    history?.take(4)?.let { historyList ->
                        items(historyList.size) {
                            CardHistorySmall(detectionHistory = history.get(it), onClick = {
                                navController.navigate(Screen.ScanDetail.route + "?id=${history.get(it).id}") {
                                    popUpTo(Screen.Main.Home.route) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            })
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)) {
                SectionTitle(text = stringResource(id = R.string.articles), fontSize = 18.sp)
                LinkText(text = stringResource(id = R.string.see_more), color = Primary, onClick = {
                    navController.navigate(Screen.Main.Article.route) {
                        popUpTo(Screen.Main.Home.route) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dummyArticleItems.take(4).size) {
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    CardArticle(articleItem = dummyArticleItems[it], onClick = {
                        navController.navigate(Screen.ArticleDetail.route + "?id=${it}") {
                            popUpTo(Screen.Main.Home.route) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
                }
            }

        }
    }
}

@Composable
fun StoreScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        BasicText(text = "Fitur ini akan segera tersedia", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ScanScreen(modifier: Modifier = Modifier, scanViewModel: ScanViewModel = koinViewModel(), navController: NavHostController) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = CropImageContract(), onResult = {
        if (it.isSuccessful) {
            imageUri = it.uriContent
            var image = UriUtil.uriToFile(imageUri!!, context)
            image = UriUtil.reduceFileImage(image)
            scanViewModel.predict(image)
        } else {
            val exception = it.error
        }
    })
    if (scanViewModel.result.value is Response.Success) {
        val result = (scanViewModel.result.value as Response.Success<DetectionHistory>).data
        if (result != null) {
            scanViewModel.result.value = Response.Empty
            navController.navigate(Screen.ScanDetail.route + "?id=${result.id}")
        }
    }
    if (imageUri != null) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            bitmap = ImageDecoder.decodeBitmap(source)
        }
    }
    Column(
        modifier = modifier
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
                    ButtonWithIcon(icon = painterResource(id = R.drawable.icon_gallery), text = stringResource(id = R.string.gallery), onClick = {
                        val cropOptions = CropImageContractOptions(uriContent, CropImageOptions(
                            imageSourceIncludeGallery = true,
                            imageSourceIncludeCamera = false
                        ))
                        launcher.launch(cropOptions)
                    })
                    ButtonWithIcon(icon = painterResource(id = R.drawable.icon_camera), text = stringResource(id = R.string.take_photo), onClick = {
                        val cropOptions = CropImageContractOptions(uriContent, CropImageOptions(
                            imageSourceIncludeGallery = false,
                            imageSourceIncludeCamera = true
                        ))
                        launcher.launch(cropOptions)
                    })
                }
            }
            if (scanViewModel.result.value is Response.Failure) {
                val message = (scanViewModel.result.value as Response.Failure).e?.message
                if (message != null) {
                    BasicText(text = message, textAlign = TextAlign.Center, color = TextDanger, modifier = Modifier.fillMaxWidth())
                }
            } else if (scanViewModel.result.value is Response.Loading) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ArticleScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        LazyColumn() {
            items(dummyHistoryItems.size) {
                Spacer(modifier = Modifier.padding(top = 8.dp))
                CardArticle(articleItem = dummyArticleItems[it], onClick = {
                    navController.navigate(Screen.ArticleDetail.route + "?id=${it}")
                })
            }
        }
    }
}

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, navController: NavHostController, historyViewModel: HistoryViewModel = koinViewModel()) {
    fun launch() {
        historyViewModel.getHistory()
    }
    if (historyViewModel.result.value is Response.Empty) {
        launch()
    }
    when (val history = historyViewModel.result.value) {
        is Response.Success -> {
            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                LazyColumn() {
                    history.data?.size?.let {
                        items(it) {
                            val detectionHistory = history.data.get(it)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            CardHistory(detectionHistory = detectionHistory, onClick = {
                                navController.navigate(Screen.ScanDetail.route + "?id=${detectionHistory.id}")
                            })
                        }
                    }
                }
            }
        }
        is Response.Failure -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.Center) {
                Column {
                    BasicText(text = stringResource(id = R.string.loading_failed))
                    TextButton(text = stringResource(id = R.string.try_again), onClick = {
                        launch()
                    })
                }
            }
        }
        else -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Primary)
            }
        }
    }
}
