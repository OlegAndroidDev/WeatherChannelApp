package com.Olehbil_channel.myweatherapp.model.weather


import com.google.gson.annotations.SerializedName

data class MainModel(
    @SerializedName("temp")
    val temp: Double,
)