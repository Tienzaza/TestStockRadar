package com.example.teststockradar.ui.dashboard

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("portfolio")
    suspend fun getPortfolio(): Response<List<PortfolioItem>>
}

data class PortfolioItem(
    val plan_id: String,
    val title: String,
    val enable: Int,
    val order: Int,
    val description: String,
    val image_plan_bg: String,
    val image_plan: String,
    val withdrawable_point: Double,
    val pending_point: Double,
    val cost: Double,
    val change: Double,
    val all: Double
){
    fun formattedPendingPoint(): String = String.format("%, .2f", pending_point)
    fun formattedChange(): String = String.format("%, .2f", change)
    fun formattedAll(): String = String.format("%, .2f", all)
}

