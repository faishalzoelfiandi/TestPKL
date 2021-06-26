package com.example.testpkl.Service


import com.example.testpkl.Model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WeatherAPI {
    //http://api.openweathermap.org/data/2.5/box/city?bbox=105,-6,114,-8,30&appid=70d78cc24e6d9131a1c25b45144d12c1
    @GET ("data/2.5/box/city?bbox=105,-6,114,-8,30&appid=70d78cc24e6d9131a1c25b45144d12c1")
    fun getData (
            @Query ("q")cityName:String ): Single<WeatherModel>

}