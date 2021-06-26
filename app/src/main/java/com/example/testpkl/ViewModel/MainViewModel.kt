package com.example.testpkl.ViewModel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testpkl.Model.Weather
import com.example.testpkl.Model.WeatherModel
import com.example.testpkl.Service.WeatherAPI
import com.example.testpkl.Service.WeatherAPIService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class MainViewModel : ViewModel() {

    private val WeatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    val weather_data = MutableLiveData<WeatherModel>()
    val weather_error = MutableLiveData<Boolean>()
    val weather_loading = MutableLiveData<Boolean>()

    fun refreshData (cityName:String){
        getDataFromAPI(cityName)

    }
    private fun getDataFromAPI (cityName: String){
        weather_loading.value = true
        disposable.add(
            WeatherAPIService.getDataService(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>(){

                    override fun onSuccess(value: WeatherModel?) {
                        weather_data.value = value
                        weather_error.value = false
                        weather_loading.value = false
                        Log.d (TAG,"On success : Success")
                    }

                    override fun onError(e: Throwable?) {
                        weather_error.value = true
                        weather_loading.value = false
                        Log.e(TAG, "onError: " + e)
                    }
                })


        )


    }
}