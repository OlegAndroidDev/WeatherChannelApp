package com.Olehbil_channel.myweatherapp.repository

import com.Olehbil_channel.myweatherapp.api.FetchAPI
import com.Olehbil_channel.myweatherapp.model.weather.WeatherModel
import com.Olehbil_channel.myweatherapp.roomdb.WeatherDAO
import com.Olehbil_channel.myweatherapp.roomdb.WeatherEntity
import com.Olehbil_channel.myweatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fetchAPI: FetchAPI,
    private val weatherDAO: WeatherDAO
) :
    Repository {

    override fun getWeather(cityName: String): Flow<Resource<WeatherModel>> {
        return flow {
            emit(Resource.Loading(true))
            val tvShowFromLocal = weatherDAO.readWeather(cityName)
            val shouldJustLoadFromCache =
                tvShowFromLocal != null && cityName.isNotBlank()
            if (shouldJustLoadFromCache) {
                emit(Resource.Success(tvShowFromLocal?.weatherModel))
                return@flow
            }
            try {
                val response = fetchAPI.getWeather(cityName)
                if (response.isSuccessful) {
                    val tvShow = response.body() as WeatherModel
                    weatherDAO.insertWeather(WeatherEntity(tvShow, cityName))
                    emit(Resource.Success(tvShow))
                } else {
                    emit(Resource.Error("Not found"))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}