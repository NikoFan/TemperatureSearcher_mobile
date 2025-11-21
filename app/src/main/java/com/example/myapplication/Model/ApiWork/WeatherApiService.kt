package com.example.myapplication.Model.ApiWork

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.Url

// Retrofit создаст реализацию этого интерфейса
interface WeatherApiService {
    // Создание строки запроса GET
    // Получается: https://api.openweathermap.org/data/2.5/weather?q=...&appid=...&units=...
    @GET("weather")
    fun getWeatherByCityName(
        // Добавляет city_name в поле ?q=
        @Query("q") city_name: String,
        // Добавляет apiKey в поле ?appid=
        @Query("appid") apiKey: String,
        // Добавляет units в поле ?units=
        @Query("units") units: String = "metric"
    ) : Call<String> // Retrofit даст ответ в виде строки JSON

    fun getRawJson(@Url url: String) : Call<String>
}