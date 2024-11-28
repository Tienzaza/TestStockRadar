package com.example.teststockradar.ui.home

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://21057034-d437-4da8-829d-b7a47400d660.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}