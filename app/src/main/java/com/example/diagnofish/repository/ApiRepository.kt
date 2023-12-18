package com.example.diagnofish.repository

import com.example.diagnofish.util.Response
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface ApiRepository {
    fun login(requestBody: RequestBody): Flow<Response<HashMap<String, String>>>
}