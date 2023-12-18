package com.example.diagnofish.service

import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(

    )
}