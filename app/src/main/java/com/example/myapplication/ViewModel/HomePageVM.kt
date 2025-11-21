package com.example.myapplication.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.Config
import com.example.myapplication.Model.ApiWork.WeatherClient
import com.example.myapplication.Model.Card
import com.example.myapplication.Model.Cities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject

class HomePageVM : ViewModel() {
    private val _card_model = mutableStateListOf<Card>() // Хранилище для карточек
    private val _cities_list = Cities().citiesList // Список городов
    private val _cities_rename = Cities().citiesReNameMap // Список переводов

    var isLoaded = false
        private set // Доступно для редактирования только внутри VM

    // Получение информации по городу
    fun fetchCityWeather(
        name_of_current_city: String // Название города
    ){
        // Call-объект из клиента
        val call_api = WeatherClient.api.getWeatherByCityName(
            city_name = name_of_current_city,
            apiKey = Config().ApiKey // Api ключ из конфига
        )

        // Асинхронный вызов считывания с API, чтобы не забивать основной поток
        call_api.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                // Проверка установки соединения с сайтом
                if (response.isSuccessful){
                    // Считывание тела ответа в String
                    val json_string: String = response.body() ?: "" // Заглушка, если ответа нет
                    add_card(json_string)

                } else {
                    // Соедниения нет
                    println("Ошибка: ${response.code()}")
                    println("Тело ошибки: ${response.errorBody()?.string()}")
                }
            }
            // Вызывается при сетевой ошибке (нет интернета, таймаут и т.д.)
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Сетевая ошибка: ${t.message}")
                t.printStackTrace()
            }
        })
    }

    // Безопасное получение карты
    fun getCardSafe(index: Int): Card? {
        return if (index < _card_model.size) _card_model[index] else null
    }
    public fun generate_card(){
        // Предварительная очистка хранилища с картами
        _card_model.clear()
        for (index_of_city in 0..(_cities_list.size-1)){
            println(_cities_list[index_of_city])
            fetchCityWeather(
                name_of_current_city = _cities_list[index_of_city]
            )
        }
    }

    // Добавление модели карточки
    private fun add_card(json_string: String){
        val json = JSONObject(json_string)

        // Извлечение имени
        val cityName = json.getString("name")

        // Извлекаем объект "main" — в нём температуры
        val main = json.getJSONObject("main")
        val temp = main.getDouble("temp").toFloat()
        val feelsLike = main.getDouble("feels_like").toFloat()

        // Извлекаем объект "wind" — в нём скорость ветра
        val wind = json.getJSONObject("wind")
        val windSpeed = wind.getDouble("speed").toFloat()    // например, 4.1

        // Возвращаем модель с нужными полями
        _card_model.add(Card(
            card_city_name = _cities_rename.get(cityName).toString(),
            card_temperature = temp,
            card_feels_like = feelsLike,
            card_wind_speed = windSpeed
        ))
    }

    public fun take_cards_count() : Int{
        return _card_model.size
    }
}