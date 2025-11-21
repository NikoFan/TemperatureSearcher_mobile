package com.example.myapplication.Model.ApiWork

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

// Объект-одиночка для создания и хранения экземпляра API-клиента
object WeatherClient {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // lazy позволяет создавать клиент api только при первом обращении
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            // Базовый URL
            .baseUrl(BASE_URL)
            // Конвертер для работы со строками (а не с JSON)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build() // Сборка экземпляра класса Retrofit по указанным данным
            .create(WeatherApiService::class.java) // Реализация работы интерфейса
    }

}