package com.example.diagnofish.repository

import com.example.diagnofish.service.ApiService
import com.example.diagnofish.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody

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

}