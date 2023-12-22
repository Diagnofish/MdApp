package com.example.diagnofish.repository

import com.example.diagnofish.model.DetectionDetail
import com.example.diagnofish.model.DetectionHistory
import com.example.diagnofish.util.Response
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

interface ApiRepository {
    fun login(requestBody: RequestBody): Flow<Response<Map<String, String>>>
    fun register(requestBody: RequestBody): Flow<Response<Map<String, String>>>
    fun detection(multipartBody: MultipartBody.Part): Flow<Response<DetectionHistory>>
    fun detectionHistory(): Flow<Response<List<DetectionHistory>>>
    fun detectionDetail(id: String): Flow<Response<DetectionDetail>>
}