package com.example.diagnofish.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diagnofish.model.DetectionDetail
import com.example.diagnofish.model.DetectionHistory
import com.example.diagnofish.repository.ApiRepository
import com.example.diagnofish.util.Response
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class DetailViewModel constructor(
    private val apiRepository: ApiRepository
): ViewModel() {
    private val _result = mutableStateOf<Response<DetectionDetail>>(Response.Empty)
    val result: MutableState<Response<DetectionDetail>> = _result

    fun getDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.detectionDetail(id).collect { response ->
                when (response) {
                    is Response.Failure -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            if (response.e is HttpException) {
                                (response.e as HttpException).response()?.errorBody()?.string()
                                    ?.let {
                                        val map = Gson().fromJson(it, Map::class.java)
                                        _result.value =
                                            Response.Failure(Exception(map["error"].toString()))
                                        Log.d("DetailViewModel", map.toString())
                                    }
                            } else {
                                _result.value = response
                                Log.d("DetailViewModel", response.e.toString())
                            }
                        }
                    }
                    else -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            _result.value = response
                            Log.d("DetailViewModel", response.toString())
                        }
                    }
                }
            }
        }
    }
}