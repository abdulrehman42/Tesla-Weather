package com.mlbench.tesla.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.repository.NetworkResult
import com.mlbench.tesla.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherVM @Inject constructor(val weatherRepo: WeatherRepo) :ViewModel(){
    val weatherResponseLiveData: MutableLiveData<NetworkResult<WeatherUpdatesModel>>
        get() = weatherRepo.weatherResponseLiveData


    fun weatherlist(context: Context) {
        viewModelScope.launch {
            try {
                weatherRepo.weather(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}