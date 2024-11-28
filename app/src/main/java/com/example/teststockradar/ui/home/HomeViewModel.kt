package com.example.teststockradar.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _lastUpdate = MutableLiveData<String>()
    val lastUpdate: LiveData<String> = _lastUpdate

    private val _stockData = MutableLiveData<List<StockData>>()
    val stockData: LiveData<List<StockData>> = _stockData

    init {
        fetchData()
    }

    private fun fetchData() {

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getIndicesData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _lastUpdate.postValue(it.last_update)
                        _stockData.postValue(it.data)
                    }
                } else {
                    Log.e("StockError", "Error can't get stock data. Code: ${response.code()}, Message: ${response.message()}")
                }
            } catch (e: Exception) {

            }
        }
    }
}