package com.mlbench.tesla.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mlbench.tesla.R
import com.mlbench.tesla.http.ApiService
import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepo @Inject constructor(val apiService: ApiService) {
    private suspend fun isNetworkAvailable(context: Context): Boolean {
        val isNetworkAvailable = withContext(Dispatchers.IO) {
            Helper.isNetworkAvailable(context)
        }

        return isNetworkAvailable
    }
    private val _weatherResponseLiveData =
        MutableLiveData<NetworkResult<WeatherUpdatesModel>>()
    val weatherResponseLiveData: MutableLiveData<NetworkResult<WeatherUpdatesModel>>
        get() = _weatherResponseLiveData

    suspend fun weather(context: Context) {

        if (isNetworkAvailable(context)) {

            _weatherResponseLiveData.postValue(NetworkResult.Loading())
            val response = apiService.getResults()
            if (response.isSuccessful) {
                _weatherResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _weatherResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
        } else {
            _weatherResponseLiveData.postValue(NetworkResult.Error(context.getString(R.string.network_not_available)))
        }
    }
}