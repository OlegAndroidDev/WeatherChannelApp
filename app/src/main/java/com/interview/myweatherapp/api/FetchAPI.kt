package com.Olehbil_channel.myweatherapp.api

import com.Olehbil_channel.myweatherapp.model.weather.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchAPI {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "7c9029dde5011ec62a103f98038e7ffc"
    ) : Response<WeatherModel>
}

