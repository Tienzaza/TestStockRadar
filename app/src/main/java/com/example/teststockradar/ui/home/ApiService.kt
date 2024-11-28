package com.example.teststockradar.ui.home

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("Indices")
    suspend fun getIndicesData(): Response<ApiResponse>
}

data class ApiResponse(
    val last_update: String,
    val data: List<StockData>
)

data class StockData(
    val symbol: String,
    val short_name: String,
    val price: Double,
    val change: Double,
    val percent_change: Double
){
    fun formattedPrice(): String = String.format("%, .2f", price)
    fun formattedChange(): String = String.format("%, .2f", change)
    fun formattedPercentChange(): String = String.format("%, .2f", percent_change) + "%"
}