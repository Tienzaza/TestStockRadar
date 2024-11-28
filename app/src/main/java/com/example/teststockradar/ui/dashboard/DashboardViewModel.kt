package com.example.teststockradar.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _portfolioItems = MutableLiveData<List<PortfolioItem>>()
    val portfolioItems: LiveData<List<PortfolioItem>> = _portfolioItems


    fun fetchPortfolio() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPortfolio()
            if (response.isSuccessful) {
                _portfolioItems.postValue(response.body())
            } else {
                Log.e("PortfolioError", "Error can't get portfolio data. Code: ${response.code()}, Message: ${response.message()}")
            }
        }
    }
}
