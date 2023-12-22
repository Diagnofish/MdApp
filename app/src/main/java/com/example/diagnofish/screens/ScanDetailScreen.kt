package com.example.diagnofish.screens

import BasicText
import ScreenTitle
import SectionTitle
import StatusResult
import TextButton
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.diagnofish.R
import com.example.diagnofish.model.HistoryItem
import com.example.diagnofish.model.dummyHistoryItems
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.util.Response
import com.example.diagnofish.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScanDetailScreen(
    id: String = "",
    navController: NavHostController = rememberNavController(),
    detailViewModel: DetailViewModel = koinViewModel()
) {
    fun launch() {
        detailViewModel.getDetail(id)
    }
    launch()
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                 IconButton(onClick = {
                     navController.popBackStack()
                 }) {
                     Image(painter = painterResource(id = R.drawable.icon_back), contentDescription = "Back")
                 }
            },
            title = { ScreenTitle(stringResource(id = R.string.result)) }
        )
    }) {
        when (val detail = detailViewModel.result.value) {
            is Response.Success -> {
                val data = detail.data!!
                Column(
                    Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(model = "https://storage.googleapis.com/diagnofish/detection-images/${data.image_filename}", contentDescription = data.fish_name, modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 360.dp), contentScale = ContentScale.Crop, fallback = painterResource(id = R.drawable.blank_image))
                    SectionTitle(text = data.fish_name)
                    StatusResult(status = if ( data.confidence_score < 0.6 ) stringResource(id = R.string.status_undetected) else data.result)
                    if (data.confidence_score >= 0.6) {
                        BasicText(text = data.description, textAlign = TextAlign.Justify)
                        if (data.symptom != "") {
                            SectionTitle(text = stringResource(id = R.string.symptom))
                            BasicText(text = data.symptom, textAlign = TextAlign.Justify)
                        }
                        if (data.cause != "") {
                            SectionTitle(text = stringResource(id = R.string.cause))
                            BasicText(text = data.cause, textAlign = TextAlign.Justify)
                        }
                        if (data.treatment != "") {
                            SectionTitle(text = stringResource(id = R.string.treatment))
                            BasicText(text = data.treatment, textAlign = TextAlign.Justify)
                        }
                        if (data.prevention != "") {
                            SectionTitle(text = stringResource(id = R.string.prevention))
                            BasicText(text = data.prevention, textAlign = TextAlign.Justify)
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
}