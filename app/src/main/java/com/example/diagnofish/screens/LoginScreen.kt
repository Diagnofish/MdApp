package com.example.diagnofish.screens

import SectionTitle
import TextButton
import TextFieldWithLabel
import LinkText
import TextAndLink
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diagnofish.R
import com.example.diagnofish.repository.UserPreferencesRepository
import com.example.diagnofish.repository.userPreferencesRepository
import com.example.diagnofish.ui.navigation.Screen
import com.example.diagnofish.ui.theme.InterFontFamily
import com.example.diagnofish.ui.theme.Primary
import com.example.diagnofish.ui.theme.TextDanger
import com.example.diagnofish.ui.theme.TextDark
import com.example.diagnofish.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Preview
@Composable
fun LoginScreen(navController: NavHostController = rememberNavController(), loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val userPreferencesRepository = UserPreferencesRepository(context)
    var emailVal = remember { mutableStateOf("") }
    var emailMsg = remember { mutableStateOf("") }
    var passVal = remember { mutableStateOf("") }
    var passMsg = remember { mutableStateOf("") }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp)
        .padding(top = 32.dp)) {
        Column {
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = TextDark)) {
                    append(stringResource(id = R.string.welcome))
                }
                append(" ")
                withStyle(style = SpanStyle(color = Primary)) {
                    append(stringResource(id = R.string.app_name))
                }
            }
            Image(painter = painterResource(id = R.drawable.fish), contentDescription = "Diagnofish Logo", modifier = Modifier
                .fillMaxWidth()
                .height(200.dp))
            Text(text = text, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp), textAlign = TextAlign.Center, fontSize = 20.sp, fontFamily = InterFontFamily, fontWeight = FontWeight.Medium)
            SectionTitle(text = stringResource(id = R.string.login), modifier = Modifier.padding(bottom = 8.dp))
            TextFieldWithLabel(Modifier.fillMaxWidth().padding(bottom = 8.dp), label = "Email", placeholder = "Email", keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email), value = emailVal, message = emailMsg)
            TextFieldWithLabel(Modifier.fillMaxWidth(), label = "Kata Sandi", placeholder = "Kata Sandi", keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), value = passVal, message = passMsg)
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), contentAlignment = Alignment.CenterEnd) {
                LinkText(text = stringResource(id = R.string.forgot_password), color = TextDanger, modifier = Modifier.align(Alignment.CenterEnd))
            }

            TextButton(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp), text = stringResource(id = R.string.login), onClick = {
                    loginViewModel.login(emailVal.value, passVal.value)
//                    navController.navigate(Screen.Main.route)
            })
            Box(modifier = Modifier.fillMaxWidth()) {
                TextAndLink(text = stringResource(id = R.string.no_account1), linkText = stringResource(id = R.string.no_account2), modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}