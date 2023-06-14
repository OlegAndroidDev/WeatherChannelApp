package com.Olehbil_channel.myweatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Olehbil_channel.myweatherapp.model.weather.WeatherModel
import com.Olehbil_channel.myweatherapp.repository.Repository
import com.Olehbil_channel.myweatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _weather : MutableStateFlow<Resource<WeatherModel>> = MutableStateFlow(Resource.Loading())
    val weather: StateFlow<Resource<WeatherModel>> get() = _weather

    fun getWeather(cityName: String) {
        repository.getWeather(cityName)
            .onEach { _weather.value = it }
            .launchIn(viewModelScope)
    }
}