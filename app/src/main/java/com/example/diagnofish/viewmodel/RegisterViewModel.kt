package com.example.diagnofish.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diagnofish.repository.ApiRepository
import com.example.diagnofish.repository.ApiRepositoryImpl
import com.example.diagnofish.util.Response
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class RegisterViewModel constructor(
    private val apiRepository: ApiRepository,
): ViewModel() {
    private val _result = mutableStateOf<Response<Map<String, String>>>(Response.Empty)
    val result: MutableState<Response<Map<String, String>>> = _result
    fun register(email: String, password: String, repeatPassword: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("repeat_password", repeatPassword)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.register(requestBody).collect { response ->
                when (response) {
                    is Response.Empty -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            _result.value = response
                        }
                    }

                    is Response.Loading -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            _result.value = response
                        }
                    }

                    is Response.Success -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            _result.value = response
                            Log.d("RegisterViewModel", response.data.toString())
                        }
                    }

                    is Response.Failure -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            if (response.e is HttpException) {
                                (response.e as HttpException).response()?.errorBody()?.string()
                                    ?.let {
                                        val map = Gson().fromJson(it, Map::class.java)
                                        _result.value =
                                            Response.Failure(Exception(map["error"].toString()))
                                        Log.d("RegisterViewModel", map.toString())
                                    }
                            } else {
                                _result.value = response
                                Log.d("RegisterViewModel", response.e.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}