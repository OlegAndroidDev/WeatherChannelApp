package com.Olehbil_channel.myweatherapp.repository

import com.Olehbil_channel.myweatherapp.model.weather.WeatherModel
import com.Olehbil_channel.myweatherapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getWeather(cityName: String) : Flow<Resource<WeatherModel>>
}