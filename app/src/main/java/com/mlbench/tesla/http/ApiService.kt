package com.mlbench.tesla.http

import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.WEATHER_API)
    suspend fun getResults(): Response<WeatherUpdatesModel>
}