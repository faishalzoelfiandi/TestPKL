package com.example.testpkl.Model

data class WeatherModel(
    val calctime: Double,
    val cnt: Int,
    val cod: Int,
    val list: List<Weather>
)