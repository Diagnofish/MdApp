package com.example.diagnofish.repository

import com.example.diagnofish.model.DetectionDetail
import com.example.diagnofish.model.DetectionHistory
import com.example.diagnofish.service.ApiService
import com.example.diagnofish.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ApiRepositoryImpl constructor(
    private val apiService: ApiService = ApiService.getInstance()
): ApiRepository {
    override fun login(requestBody: RequestBody): Flow<Response<Map<String, String>>> = flow {
        emit(Response.Loading)
        try {
            val response = apiService.login(requestBody)
            emit(Response.Success(response))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun register(requestBody: RequestBody): Flow<Response<Map<String, String>>> {
        return flow {
            emit(Response.Loading)
            try {
                val response = apiService.register(requestBody)
                emit(Response.Success(response))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }

    override fun detection(multipartBody: MultipartBody.Part): Flow<Response<DetectionHistory>> {
        return flow {
            emit(Response.Loading)
            try {
                val response = apiService.detection(multipartBody)
                emit(Response.Success(response))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }

    override fun detectionHistory(): Flow<Response<List<DetectionHistory>>> {
        return flow {
            emit(Response.Loading)
            try {
                val response = apiService.detectionHistory()
                emit(Response.Success(response))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }

    override fun detectionDetail(id: String): Flow<Response<DetectionDetail>> {
        return flow {
            emit(Response.Loading)
            try {
                val response = apiService.detectionDetail(id)
                emit(Response.Success(response))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }
}