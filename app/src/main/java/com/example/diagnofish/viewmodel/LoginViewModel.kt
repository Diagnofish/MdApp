package com.example.diagnofish.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diagnofish.repository.ApiRepository
import com.example.diagnofish.repository.ApiRepositoryImpl
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class LoginViewModel constructor(
    private val apiRepository: ApiRepository = ApiRepositoryImpl()
): ViewModel() {
    fun login(email: String, password: String, success: MutableState<Boolean>, result: MutableState<String>) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.login(requestBody).collect { response ->
                when(response) {
                    is com.example.diagnofish.util.Response.Loading -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            success.value = false
                        }
                    }
                    is com.example.diagnofish.util.Response.Success -> {
                        CoroutineScope(Dispatchers.Main).launch {
//                            success.value = true
//                            result.value = response.data["message"].toString()
                            Log.d("LoginViewModel", response.data.toString())
                        }
                    }
                    is com.example.diagnofish.util.Response.Failure -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            if (response.e is HttpException)
                                (response.e as HttpException).response()?.errorBody()?.string()?.let {
                                    Log.d("LoginViewModel", it)
                                    Gson().fromJson(it, JsonObject::class.java)
                                }
                            else
                                Log.d("LoginViewModel", response.e.toString())
                        }
                    }
                }
            }
        }
    }
}